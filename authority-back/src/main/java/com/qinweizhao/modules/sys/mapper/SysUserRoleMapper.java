package com.qinweizhao.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinweizhao.modules.sys.entity.SysRole;
import com.qinweizhao.modules.sys.entity.SysUserRole;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 通过用户名查询所属角色
     * @param id id
     * @return List<SysRole>
     */
    List<SysRole> selectRolesByUserId(Long id);
}
