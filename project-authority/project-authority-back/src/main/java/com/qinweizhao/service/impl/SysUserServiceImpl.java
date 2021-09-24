package com.qinweizhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qinweizhao.entity.SysUser;
import com.qinweizhao.mapper.SysUserMapper;
import com.qinweizhao.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
