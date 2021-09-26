package com.qinweizhao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinweizhao.entity.SysUser;

import java.util.Map;
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
    Map<String, String> selectUserInfoByUsername(String username);

    /**
     * 通过用户 Id 查询拥有的权限
     *
     * @param userId 用户Id
     * @return 权限集合
     */
    Set<String> selectPermissionByUserId(Long userId);
}
