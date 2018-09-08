package cn.zhyonk.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.zhyonk.entity.Login;
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

	@Override
	public String checkLogin(Login login) {
		login.getLoginName();
		login.getPassword();
		
		return "123456";
	}

	@Override
	public User getUserByLoginId(String loginId) {
		User user = new User();
		user.setId(12000L);
		user.setAge(25);
		user.setName("zhyonk");
		return null;
	}

	@Override
	public Set<String> loadRoles(String clientKey) {
		// TODO Auto-generated method stub
		Set<String> hashSet = new HashSet<>();
		hashSet.add("aa");
		hashSet.add("bb");
		return hashSet;
	}

	@Override
	public Set<String> loadPermissions(String clientKey) {
		Set<String> hashSet = new HashSet<>();
		hashSet.add("aa");
		hashSet.add("bb");
		return hashSet;
	}


}
