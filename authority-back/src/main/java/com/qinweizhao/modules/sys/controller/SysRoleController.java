package com.qinweizhao.modules.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qinweizhao.common.controller.BaseController;
import com.qinweizhao.common.entity.R;
import com.qinweizhao.modules.sys.entity.SysRole;
import com.qinweizhao.modules.sys.entity.SysRoleMenu;
import com.qinweizhao.modules.sys.entity.SysUserRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {


    private static final String ROLE_ID = "role_id";

    /**
     * 新增角色
     *
     * @param sysRole sysRole
     * @return R
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:role:save')")
    public R save(@Validated @RequestBody SysRole sysRole) {
        sysRole.setStatus("0");
        sysRoleService.save(sysRole);
        return R.success(sysRole);
    }


    /**
     * 删除
     *
     * @param ids ids
     * @return r
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @Transactional(rollbackFor = Exception.class)
    public R delete(@RequestBody Long[] ids) {

        sysRoleService.removeByIds(Arrays.asList(ids));
        // 删除中间表
        sysUserRoleService.remove(new QueryWrapper<SysUserRole>().in(ROLE_ID, (Object) ids));
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().in(ROLE_ID, (Object) ids));
        return R.success("");
    }


    /**
     * 修改
     *
     * @param sysRole sysRole
     * @return r
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public R update(@Validated @RequestBody SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        List<Long> menuIds = sysRole.getMenuIds();
        Long roleId = sysRole.getRoleId();
        List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
        menuIds.forEach(menuId -> {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            sysRoleMenus.add(roleMenu);
        });
        // 先删除原来的记录，再保存新的
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq(ROLE_ID, roleId));
        sysRoleMenuService.saveBatch(sysRoleMenus);
        return R.success(sysRole);
    }


    /**
     * 获取角色详情
     *
     * @param id id
     * @return r
     */
    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        // 获取角色相关联的菜单id
        List<SysRoleMenu> roleMenus = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq(ROLE_ID, id));
        List<Long> menuIds = roleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        sysRole.setMenuIds(menuIds);
        return R.success(sysRole);
    }

    /**
     * 分页信息
     *
     * @param name name
     * @return r
     */
    @PreAuthorize("hasAuthority('sys:role:page')")
    @GetMapping("/page")
    public R page(String name) {
        return R.success(sysRoleService.page(getPage(),
                new QueryWrapper<SysRole>()
                        .like(StrUtil.isNotBlank(name), "name", name)
        ));
    }

    /**
     * 列表信息
     *
     * @return r
     */
    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/list")
    public R list() {
        return R.success(sysRoleService.list());
    }
}
