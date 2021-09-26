package com.qinweizhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qinweizhao.entity.SysUser;
import com.qinweizhao.mapper.SysUserMapper;
import com.qinweizhao.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

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

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户名，密码，状态
     */
    @Override
    public Map<String, String> selectUserInfoByUsername(String username) {
        return this.baseMapper.selectUserInfoByUsername(username);
    }

    /**
     * 通过用户 Id 查询拥有的权限
     *
     * @param userId 用户Id
     * @return 权限集合
     */
    @Override
    public Set<String> selectPermissionByUserId(Long userId) {
        return this.baseMapper.selectPermissionByUserId(userId);
    }
}
