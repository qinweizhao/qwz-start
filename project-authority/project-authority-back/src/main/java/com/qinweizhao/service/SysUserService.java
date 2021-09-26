package com.qinweizhao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinweizhao.entity.SysUser;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户名，密码，状态
     */
    Map<String, String> selectUserInfoByUsername(String username);

    /**
     * 通过用户 Id 查询拥有的权限
     *
     * @param userId 用户Id
     * @return 权限集合
     */
    Set<String> selectPermissionByUserId(Long userId);
}
