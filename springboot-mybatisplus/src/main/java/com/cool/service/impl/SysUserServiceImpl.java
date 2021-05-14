package com.cool.service.impl;

import com.cool.entity.SysUser;
import com.cool.mapper.SysUserMapper;
import com.cool.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统管理-用户基础信息表 服务实现类
 * </p>
 *
 * @author water33
 * @since 2021-05-12
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
