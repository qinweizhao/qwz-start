package com.qinweizhao.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinweizhao.modules.sys.entity.SysUser;
import com.qinweizhao.common.security.entity.SysUserDetails;

import java.io.IOException;

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
    SysUserDetails getUserDetailsInfoByUsername(String username);

    /**
     * 通过用户 Id 查询拥有的权限
     *
     * @param userId 用户Id
     * @return 权限集合
     */
    String getAuthorityByUserId(String userId);

    /**
     * 获取验证码
     *
     * @return base64编码
     * @throws IOException e
     */
    String getCaptcha() throws IOException;

    /**
     * 通过用户名获取用户信息
     *
     * @param name 用户名
     * @return SysUser
     */
    SysUser getSysUserByUsername(String name);
}
