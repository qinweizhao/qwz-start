package com.qinweizhao.modules.sys.controller;


import cn.hutool.core.map.MapUtil;
import com.qinweizhao.common.controller.BaseController;
import com.qinweizhao.common.entity.R;
import com.qinweizhao.common.entity.dto.PassDto;
import com.qinweizhao.modules.sys.entity.SysUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.Principal;


/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * 保存
     *
     * @param sysUser sysUser
     * @return r
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:user:save')")
    public R save(@Validated @RequestBody SysUser sysUser) {
        return getR(sysUserService.savaSysUser(sysUser));
    }


    /**
     * 批量删除
     *
     * @param ids ids
     * @return r
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public R delete(@RequestBody Long[] ids) {
        return getR(sysUserService.removeSysUser(ids));
    }


    /**
     * 修改密码
     *
     * @param passDto   密码
     * @param principal 认证主体
     * @return r
     */
    @PostMapping("/updatePwd")
    public R updatePass(@Validated @RequestBody PassDto passDto, Principal principal) {
        SysUser sysUser = sysUserService.getSysUserByUsername(principal.getName());
        boolean matches = passwordEncoder.matches(passDto.getOldPassword(), sysUser.getPassword());
        if (!matches) {
            return R.failure("旧密码不正确");
        }
        sysUser.setPassword(passwordEncoder.encode(passDto.getNewPassword()));
        sysUserService.updateById(sysUser);
        return R.success("");
    }

    /**
     * 修改
     *
     * @param sysUser sysUser
     * @return r
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:user:update')")
    public R update(@Validated @RequestBody SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return R.success(sysUser);
    }


    /**
     * 获取验证码
     *
     * @return r
     * @throws IOException e
     */
    @GetMapping("/captcha")
    public R captcha() throws IOException {
        return R.success(sysUserService.getCaptcha());
    }

    /**
     * 登录成功获取用户信息
     *
     * @param principal principal
     * @return r
     */
    @GetMapping("/info")
    public R info(Principal principal) {
        SysUser sysUser = sysUserService.getSysUserByUsername(principal.getName());
        return R.success(MapUtil.builder()
                .put("id", sysUser.getUserId())
                .put("username", sysUser.getUsername())
                .put("avatar", sysUser.getAvatar())
                .map()
        );
    }

    /**
     * 获取用户信息
     *
     * @param id id
     * @return r
     */
    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.success(sysUserService.getInfoById(id));
    }

    /**
     * 用户列表（分页）
     *
     * @param username username
     * @return r
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public R page(String username) {
        return R.success(sysUserService.pageSysUsers(getPage(), username));
    }


}
