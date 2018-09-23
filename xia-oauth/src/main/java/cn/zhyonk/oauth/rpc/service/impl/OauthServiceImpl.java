package cn.zhyonk.oauth.rpc.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.zhyonk.common.utils.DESUtils;
import cn.zhyonk.entity.Login;
import cn.zhyonk.entity.User;
import cn.zhyonk.oauth.mapper.OauthMapper;
import cn.zhyonk.rpc.api.OauthService;

/**
*
* User 表数据服务层接口实现类
*
*/
@Service("oauthService")
public class OauthServiceImpl extends ServiceImpl<OauthMapper, User> implements OauthService {

	@Autowired
	private OauthMapper oauthMapper;
	
	@Override
	public String checkLogin(Login login) {
		String phone = login.getPhone();
		String password = DESUtils.getDecryptString(login.getPassword());
		String openid = oauthMapper.checkLogin(phone,password);
		return openid;
	}

	@Override
	public User getUserByOpenId(String uid) {
		User selectById = oauthMapper.selectByOprnId(uid);
		return selectById;
	}

	@Override
	public Set<String> loadRoles(String uid) {
		Set<String> hashSet = new HashSet<>();
		return hashSet;
	}

	@Override
	public Set<String> loadPermissions(String uid) {
		Set<String> hashSet = new HashSet<>();
		hashSet.add("aa");
		hashSet.add("bb");
		return hashSet;
	}


}
