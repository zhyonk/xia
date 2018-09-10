package cn.zhyonk.mapper;


import org.apache.ibatis.annotations.Param;

import cn.zhyonk.common.SuperMapper;
import cn.zhyonk.entity.User;

/**
 *
 * User 表数据库控制层接口
 *
 */
public interface UserMapper extends SuperMapper<User> {

	String checkLogin(@Param(value = "phone") String phone,@Param(value = "password") String password);

	User selectByOprnId(@Param(value = "openid") String uid);

}
