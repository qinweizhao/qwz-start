package com.qinweizhao.service.impl;

import cn.hutool.core.codec.Base64;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.qinweizhao.common.entity.Constant;
import com.qinweizhao.entity.SysUser;
import com.qinweizhao.entity.SysUserDetails;
import com.qinweizhao.mapper.SysUserMapper;
import com.qinweizhao.service.SysUserService;
import com.qinweizhao.util.GuavaCacheUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    DefaultKaptcha defaultKaptcha;

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户名，密码，状态
     */
    @Override
    public SysUserDetails selectUserInfoByUsername(String username) {
        return this.baseMapper.selectUserInfoByUsername(username);
    }

    /**
     * 通过用户 Id 查询拥有的权限
     *
     * @param userId 用户Id
     * @return 权限集合
     */
    @Override
    public String selectAuthorityByUserId(String userId) {
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
        if (log.isDebugEnabled()){
            log.debug("验证码为:"+text);
        }
        String key = Constant.LOGIN_CODE + "_" + RandomStringUtils.random(5);
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
}
