package com.qinweizhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qinweizhao.entity.SysUserRole;
import com.qinweizhao.mapper.SysUserRoleMapper;
import com.qinweizhao.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
