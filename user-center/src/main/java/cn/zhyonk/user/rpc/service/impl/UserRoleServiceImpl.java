package cn.zhyonk.user.rpc.service.impl;

import cn.zhyonk.entity.UserRole;
import cn.zhyonk.rpc.api.UserRoleService;
import cn.zhyonk.user.mapper.UserRoleMapper;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色表 服务实现类
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-22
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
