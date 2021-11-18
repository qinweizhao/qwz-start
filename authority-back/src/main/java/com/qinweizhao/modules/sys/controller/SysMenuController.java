package com.qinweizhao.modules.sys.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qinweizhao.common.controller.BaseController;
import com.qinweizhao.common.entity.R;
import com.qinweizhao.common.entity.dto.SysMenuDTO;
import com.qinweizhao.modules.sys.entity.SysMenu;
import com.qinweizhao.modules.sys.entity.SysRoleMenu;
import com.qinweizhao.modules.sys.entity.SysUser;
import com.qinweizhao.modules.sys.service.SysMenuService;
import com.qinweizhao.modules.sys.service.SysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;


/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysUserService sysUserService;


    /**
     * 新增
     *
     * @param sysMenu sysMenu
     * @return r
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:menu:save')")
    public R save(@Validated @RequestBody SysMenu sysMenu) {
        return getR(sysMenuService.save(sysMenu));
    }

    /**
     * 删除菜单,如果子菜单存在则不允许删除
     *
     * @param id id
     * @return r
     */
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public R delete(@PathVariable("id") Long id) {

        int count = sysMenuService.count(new QueryWrapper<SysMenu>().eq("parent_id", id));
        if (count > 0) {
            return R.failure("请先删除子菜单");
        }

        sysMenuService.removeById(id);
        // 同步删除中间关联表
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("menu_id", id));
        return R.success("");
    }


    /**
     * 修改菜单
     *
     * @param sysMenu sysMenu
     * @return r
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    public R update(@Validated @RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return R.success(sysMenu);
    }


    /**
     * 获取用户当前的菜单和权限信息
     *
     * @param principal principal
     * @return r
     */
    @GetMapping("/nav")
    public R nav(Principal principal) {
        SysUser sysUser = sysUserService.getSysUserByUsername(principal.getName());
        String authority = sysUserService.getAuthorityByUserId(sysUser.getUserId());
        List<SysMenuDTO> menuDTOList = sysMenuService.listMenuDTO(sysUser.getUserId());
        return R.success(MapUtil.builder()
                .put("Authority", authority)
                .put("nav", menuDTOList)
                .map()
        );
    }


    /**
     * 获取树形菜单
     *
     * @return r
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public R list() {
        return R.success(sysMenuService.tree());
    }

    /**
     * 获取菜单详情
     *
     * @param id id
     * @return r
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public R info(@PathVariable(name = "id") Long id) {
        return R.success(sysMenuService.getById(id));
    }

}
