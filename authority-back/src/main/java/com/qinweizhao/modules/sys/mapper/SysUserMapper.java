package com.qinweizhao.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinweizhao.modules.sys.entity.SysUser;
import com.qinweizhao.common.security.entity.SysUserDetails;

import java.util.Set;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户名，密码，状态
     */
    SysUserDetails selectUserInfoByUsername(String username);

    /**
     * 通过用户 Id 查询拥有的权限
     *
     * @param userId 用户 Id
     * @return 权限集合
     */
    Set<String> selectPermissionByUserId(String userId);

    /**
     * 通过用户 Id 查询拥有的角色
     *
     * @param userId 用户 Id
     * @return 角色集合
     */
    Set<String> selectRoleByUserId(String userId);


    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return SysUser
     */
    SysUser getSysUserByUsername(String username);
}
