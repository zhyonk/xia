package cn.zhyonk.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.zhyonk.entity.User;
import cn.zhyonk.mapper.UserMapper;
import cn.zhyonk.service.IUserService;

/**
*
* User 表数据服务层接口实现类
*
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


}
