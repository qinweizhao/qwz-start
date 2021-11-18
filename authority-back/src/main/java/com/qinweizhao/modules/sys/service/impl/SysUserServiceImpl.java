package com.qinweizhao.modules.sys.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.qinweizhao.common.entity.Constant;
import com.qinweizhao.common.security.entity.SysUserDetails;
import com.qinweizhao.common.util.GuavaCacheUtils;
import com.qinweizhao.modules.sys.entity.SysRole;
import com.qinweizhao.modules.sys.entity.SysUser;
import com.qinweizhao.modules.sys.entity.SysUserRole;
import com.qinweizhao.modules.sys.mapper.SysUserMapper;
import com.qinweizhao.modules.sys.mapper.SysUserRoleMapper;
import com.qinweizhao.modules.sys.service.SysUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Resource
    private DefaultKaptcha defaultKaptcha;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户名，密码，状态
     */
    @Override
    public SysUserDetails getUserDetailsInfoByUsername(String username) {
        return this.baseMapper.selectUserInfoByUsername(username);
    }

    /**
     * 通过用户 Id 查询拥有的权限
     *
     * @param userId 用户Id
     * @return 权限集合
     */
    @Override
    public String getAuthorityByUserId(Long userId) {
        String authority = "";
        Set<String> roleSet = this.baseMapper.selectRoleByUserId(userId);
        if (!roleSet.isEmpty()) {
            String roles = roleSet.stream().map("ROLE_"::concat).collect(Collectors.joining(","));
            log.debug("当前用户拥有的角色有:" + roles);
            authority = authority.concat(",");
        }
        Set<String> permissionSet = this.baseMapper.selectPermissionByUserId(userId);

        if (!permissionSet.isEmpty()) {
            String permission = String.join(",", permissionSet);
            log.debug("当前用户拥有的菜单权限有:" + permission);
            authority = authority.concat(permission);
            log.debug("当前用户拥有的菜单权限有:" + authority);
        }
        return authority;
    }

    /**
     * 获取验证码
     *
     * @return base64编码
     */
    @Override
    public String getCaptcha() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String text = defaultKaptcha.createText();
        if (log.isDebugEnabled()) {
            log.debug("验证码为:" + text);
        }
        String key = Constant.LOGIN_CODE_KEY + "_" + RandomStringUtils.random(5);
        GuavaCacheUtils.CACHE.put(key, text);
        BufferedImage image = defaultKaptcha.createImage(text);
        ImageIO.write(image, "jpg", outputStream);
        outputStream.flush();
        String str = "data:image/jpeg;base64,";
        return str + Base64.encode(outputStream.toByteArray());
    }

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return SysUser
     */
    @Override
    public SysUser getSysUserByUsername(String username) {
        return this.baseMapper.getSysUserByUsername(username);
    }

    /**
     * 用户列表（分页）
     * @param page 分页
     * @param username 用户名
     * @return Page<SysUser>
     */
    @Override
    public Page<SysUser> pageSysUsers(Page<SysUser> page, String username) {
        Page<SysUser> pageData = this.baseMapper.selectPage(page, new QueryWrapper<SysUser>()
                .like(StrUtil.isNotBlank(username), "username", username));
        pageData.getRecords().forEach(item ->
                item.setSysRoles(sysUserRoleMapper.selectRolesByUserId(item.getUserId()))
        );
        return pageData;
    }

    /**
     * 通过用户 id 查询用户
     * @param id id
     * @return SysUser
     */
    @Override
    public SysUser getInfoById(Long id) {
        SysUser sysUser = this.baseMapper.selectById(id);
        Assert.notNull(sysUser, "找不到该管理员");
        List<SysRole> roles = sysUserRoleMapper.selectRolesByUserId(id);
        sysUser.setSysRoles(roles);
        return sysUser;
    }

    /**
     * 删除用户
     * @param ids ids
     * @return b
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeSysUser(Long[] ids) {
        int i = this.baseMapper.deleteBatchIds(Arrays.asList(ids));
        sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().in("user_id", (Object) ids));
        return i>0;
    }
}
