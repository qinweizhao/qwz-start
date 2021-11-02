package com.qinweizhao.modules.sys.controller;


import cn.hutool.core.map.MapUtil;
import com.qinweizhao.common.entity.dto.SysMenuDTO;
import com.qinweizhao.common.entity.CommonResponse;
import com.qinweizhao.modules.sys.entity.SysUser;
import com.qinweizhao.modules.sys.service.SysMenuService;
import com.qinweizhao.modules.sys.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/nav")
    public CommonResponse nav(Principal principal) {
        SysUser sysUser = sysUserService.getSysUserByUsername(principal.getName());
        String authority = sysUserService.selectAuthorityByUserId(sysUser.getUserId().toString());
        List<SysMenuDTO> menuDTO = sysMenuService.getCurrentUserNavigation(sysUser.getUserId());
        return CommonResponse.success(MapUtil.builder()
                .put("Authority",authority)
                .put("nav",menuDTO)
                .map()
        );
    }

}
