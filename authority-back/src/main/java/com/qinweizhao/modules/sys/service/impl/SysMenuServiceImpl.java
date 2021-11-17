package com.qinweizhao.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qinweizhao.common.entity.dto.SysMenuDTO;
import com.qinweizhao.modules.sys.entity.SysMenu;
import com.qinweizhao.modules.sys.mapper.SysMenuMapper;
import com.qinweizhao.modules.sys.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private static final Long LONG_ZERO = 0L;

    @Override
    public List<SysMenuDTO> listMenuDTO(Long userId) {
        List<SysMenuDTO> menus = this.baseMapper.selectMenuListByUserId(userId);
        return menus.stream().filter(item ->
                LONG_ZERO.equals(item.getParentId())
        ).peek(item ->item.setChildren(getChildrenMenu(item, menus))).collect(Collectors.toList());
    }

    /**
     * 获取子菜单
     *
     * @param menu  菜单vo d
     * @param menus 收集菜单的容器
     * @return 返回容器
     */
    private List<SysMenuDTO> getChildrenMenu(SysMenuDTO menu, List<SysMenuDTO> menus) {
        menus.stream().filter(
                i -> menu.getMenuId().equals(i.getParentId())
        ).forEach(item -> item.setChildren(getChildrenMenu(item, menus)));


        System.out.println(menus);
        return menus.stream().filter(
                i -> menu.getMenuId().equals(i.getParentId())
        ).peek(item -> item.setChildren(getChildrenMenu(item, menus))).collect(Collectors.toList());
    }

}
