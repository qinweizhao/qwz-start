package com.qinweizhao.modules.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qinweizhao.common.entity.R;
import com.qinweizhao.modules.sys.entity.SysUser;
import com.qinweizhao.modules.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qinweizhao
 * @since 2021/9/24
 */
public class BaseController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    /**
     * 获取页面
     *
     * @return page
     */
    public <T> Page<T> getPage() {
        int current = ServletRequestUtils.getIntParameter(request, "current", 1);
        int size = ServletRequestUtils.getIntParameter(request, "size", 10);
        return new Page<>(current, size);
    }

    /**
     * 获取响应
     *
     * @param b b
     * @return r
     */
    public R getR(boolean b) {
        return b ? R.success("成功") : R.failure("失败");
    }
}
