package com.qinweizhao.controller;


import com.qinweizhao.common.entity.CommonResponse;
import com.qinweizhao.entity.SysUser;
import com.qinweizhao.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @GetMapping("/list")
    public CommonResponse list() {
        List<SysUser> list = sysUserService.list(null);
        return CommonResponse.success(list);
    }
}
