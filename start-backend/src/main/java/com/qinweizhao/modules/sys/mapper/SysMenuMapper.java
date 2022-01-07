package com.qinweizhao.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinweizhao.common.entity.dto.SysMenuDTO;
import com.qinweizhao.modules.sys.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 查询用户所拥有的菜单
     *
     * @param userId userId
     * @return List<SysMenuDTO>
     */
    List<SysMenuDTO> selectMenuListByUserId(Long userId);
}
