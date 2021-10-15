package com.qinweizhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qinweizhao.common.dto.SysMenuDTO;
import com.qinweizhao.entity.SysMenu;
import com.qinweizhao.mapper.SysMenuMapper;
import com.qinweizhao.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<SysMenuDTO> getCurrentUserNavigation(Long userId) {
        return null;
    }
}
