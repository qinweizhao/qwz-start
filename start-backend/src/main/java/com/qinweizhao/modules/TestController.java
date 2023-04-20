package com.qinweizhao.modules;
import com.google.common.collect.Lists;

import com.qinweizhao.modules.sys.entity.SysUser;
import com.qinweizhao.modules.sys.entity.SysUserRole;
import com.qinweizhao.modules.sys.mapper.SysUserMapper;
import com.qinweizhao.modules.sys.mapper.SysUserRoleMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinweizhao
 * @since 2023-04-20
 */
@RestController
public class TestController {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @RequestMapping("/test")
    public String test(){
        SysUser sysUser = new SysUser();
        sysUser.setUserId(123L);
        sysUser.setUsername("1");
        sysUser.setPassword("1");
        sysUser.setNickName("1");
        sysUser.setAvatar("1");
        sysUser.setStatus("1");

        int insert = sysUserMapper.insert(sysUser);

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(123L);
        sysUserRole.setRoleId(123L);

        sysUserRoleMapper.insert(sysUserRole);

        System.out.println(insert);


        return "执行结束"+insert;
    }

}
