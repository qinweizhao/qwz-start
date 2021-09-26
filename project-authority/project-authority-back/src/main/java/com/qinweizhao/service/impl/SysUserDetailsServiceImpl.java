package com.qinweizhao.service.impl;

import com.qinweizhao.entity.SysUserDetails;
import com.qinweizhao.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
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
        SysUserDetails sysUserDetails = new SysUserDetails();
        Map<String, String> accountInfo = sysUserService.selectUserInfoByUsername(username);
        BeanUtils.copyProperties(accountInfo, sysUserDetails);
        sysUserDetails.setPassword("$2a$10$H48FBiVBYHX.4n9Afp73N.885w9gaQZxs4Zj50MkvsolEIyK5gnfC");
        Set<String> permission = sysUserService.selectPermissionByUserId(Long.parseLong(String.valueOf(accountInfo.get("user_id"))));
        String authority = String.join(",", permission);
        log.info("当前用户拥有的权限有{}", authority);
        sysUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(authority));
        return sysUserDetails;
    }
}
