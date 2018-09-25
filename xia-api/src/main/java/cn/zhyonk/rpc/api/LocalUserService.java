package cn.zhyonk.rpc.api;

import com.baomidou.mybatisplus.service.IService;

import cn.zhyonk.entity.WechatUser;

public interface LocalUserService extends IService<WechatUser> {

    WechatUser selectUserInfoByOpenid(String openid);

    void insertRelation(String fuid, String ziid);

    String selectOpenidByZiOpenid(String openid);

    void insertOrUpdateRelation(String fuid, String ziid);

    boolean isRelation(String fuid, String ziid);
}
