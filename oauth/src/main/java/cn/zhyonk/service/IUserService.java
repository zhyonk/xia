package cn.zhyonk.service;

import java.util.Set;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.IService;

import cn.zhyonk.entity.Login;
import cn.zhyonk.entity.User;

/**
*
* User 表数据服务层接口
*
*/
@Service
public interface IUserService extends IService<User> {

	String checkLogin(Login login);


	User getUserByOpenId(String loginId);


	Set<String> loadRoles(String clientKey);


	Set<String> loadPermissions(String clientKey);


}