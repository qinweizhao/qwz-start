package com.qinweizhao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinweizhao.common.dto.SysMenuDTO;
import com.qinweizhao.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取当前用户的菜单导航
     * @param userId 用户id
     * @return list
     */
    List<SysMenuDTO> getCurrentUserNavigation(Long userId);
}
