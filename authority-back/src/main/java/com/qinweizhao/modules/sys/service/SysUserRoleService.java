package com.qinweizhao.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinweizhao.modules.sys.entity.SysRole;
import com.qinweizhao.modules.sys.entity.SysUserRole;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 服务类
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
public interface SysUserRoleService extends IService<SysUserRole> {


    /**
     * 通过用户 id 获取所属角色集合
     * @param id id
     * @return List<SysRole>
     */
    List<SysRole> listRolesByUserId(Long id);

}
