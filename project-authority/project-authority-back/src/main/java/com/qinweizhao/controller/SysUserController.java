package com.qinweizhao.controller;


import com.qinweizhao.common.entity.CommonResponse;
import com.qinweizhao.entity.SysUser;
import com.qinweizhao.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


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

    @GetMapping("/captcha")
    public CommonResponse captcha() throws IOException {
        return CommonResponse.success(sysUserService.getCaptcha());
    }


    @GetMapping("/list")
    public CommonResponse list() {
        List<SysUser> list = sysUserService.list(null);
        return CommonResponse.success(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse delete(@PathVariable("id")String id){
        boolean b = sysUserService.removeById(id);
        return getCommonResponse(b);
    }

    @PutMapping("/update")
    public CommonResponse update(SysUser sysUser){
        boolean b = sysUserService.updateById(sysUser);
        return getCommonResponse(b);
    }
}
