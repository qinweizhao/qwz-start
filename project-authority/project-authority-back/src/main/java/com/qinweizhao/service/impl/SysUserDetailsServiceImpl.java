package com.qinweizhao.service.impl;

import com.qinweizhao.entity.SysUserDetails;
import com.qinweizhao.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;


/**
 * @author qinweizhao
 * @since 2021/9/25
 */
@Service("sysUserDetailsService")
public class SysUserDetailsServiceImpl implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDetails accountInfo = sysUserService.selectUserInfoByUsername(username);
        if (accountInfo == null) {
            throw new UsernameNotFoundException("用户名输入错误");
        }
        Set<String> permission = sysUserService.selectPermissionByUserId(Long.parseLong(accountInfo.getUserId()));
        String authority = String.join(",", permission);
        log.info("当前用户拥有的权限有{}", authority);
        accountInfo.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(authority));
        return accountInfo;
    }
}
