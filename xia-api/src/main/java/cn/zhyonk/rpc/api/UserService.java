package cn.zhyonk.rpc.api;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.service.IService;

import cn.zhyonk.entity.Login;
import cn.zhyonk.entity.MemberCard;
import cn.zhyonk.entity.MemberCardInfo;
import cn.zhyonk.entity.Permission;
import cn.zhyonk.entity.User;

/**
*
* User 表数据服务层接口
*
*/
public interface UserService extends IService<User> {

	String checkLogin(Login login);
	
	User checkUser(Login login);

	User getUserByOpenId(String loginId);

	Set<String> loadRoles(String openid);

	Set<String> loadPermissions(String openid);

	Set<Permission> getPermission(String openid);

	List<MemberCard> selectMemberCardList(String openid);

	MemberCardInfo getMemberCardByNumber(String number);


}