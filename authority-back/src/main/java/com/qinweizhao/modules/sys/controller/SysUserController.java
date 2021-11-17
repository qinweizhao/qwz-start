package com.qinweizhao.modules.sys.controller;


import cn.hutool.core.map.MapUtil;
import com.qinweizhao.common.entity.R;
import com.qinweizhao.modules.sys.entity.SysUser;
import com.qinweizhao.modules.sys.service.SysUserService;
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
    private SysUserService sysUserService;

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

    @DeleteMapping("/delete")
    public R delete(String id) {
        boolean b = sysUserService.removeById(id);
        return getR(b);
    }

    @PutMapping("/update")
    public R update(SysUser sysUser) {
        boolean b = sysUserService.updateById(sysUser);
        return getR(b);
    }
}
