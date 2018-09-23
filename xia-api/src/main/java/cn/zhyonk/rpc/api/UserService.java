package cn.zhyonk.rpc.api;

import java.util.Set;

import com.baomidou.mybatisplus.service.IService;

import cn.zhyonk.entity.Login;
import cn.zhyonk.entity.User;

/**
*
* User 表数据服务层接口
*
*/
public interface UserService extends IService<User> {

	String checkLogin(Login login);


	User getUserByOpenId(String loginId);


	Set<String> loadRoles(String clientKey);


	Set<String> loadPermissions(String clientKey);


}