package cn.zhyonk.user.rpc.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.zhyonk.common.utils.DESUtils;
import cn.zhyonk.entity.Login;
import cn.zhyonk.entity.Permission;
import cn.zhyonk.entity.User;
import cn.zhyonk.rpc.api.UserService;
import cn.zhyonk.user.mapper.UserMapper;

/**
*
* User 表数据服务层接口实现类
*
*/
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public String checkLogin(Login login) {
		String phone = login.getPhone();
		String password = DESUtils.getDecryptString(login.getPassword());
		String openid = userMapper.checkLogin(phone,password);
		return openid;
	}

	@Override
	public User getUserByOpenId(String uid) {
		User selectById = userMapper.selectByOpenId(uid);
		return selectById;
	}

	@Override
	public Set<String> loadRoles(String openid) {
		List<String> roles = userMapper.loadRoles(openid);
		
		Set<String> set = new HashSet<>(roles);
		return set;
	}

	@Override
	public Set<String> loadPermissions(String openid) {
		List<Permission> permissionList = userMapper.loadPermissions(openid);
		Set<String> perSet = new HashSet<>();
		for (Permission per : permissionList) {
			perSet.add(per.getPermission());
		}
		return perSet;
	}
	
	@Override
	public Set<Permission> getPermission(String openid) {
		List<Permission> permissionList = userMapper.loadPermissions(openid);
		Set<Permission> perSet = new HashSet<Permission>(permissionList);
		return perSet;
	}

	@Override
	public User checkUser(Login login) {
		String openid = login.getPhone();
		User user = userMapper.checkUser(openid);
		return user;
	}
}
