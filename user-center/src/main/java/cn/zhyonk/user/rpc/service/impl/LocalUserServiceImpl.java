package cn.zhyonk.user.rpc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.zhyonk.entity.WechatUser;
import cn.zhyonk.rpc.api.LocalUserService;
import cn.zhyonk.user.mapper.LocalUserMapper;

@Service("localUserService")
public class LocalUserServiceImpl extends ServiceImpl<LocalUserMapper,WechatUser> implements LocalUserService{
	
    @Override
    public WechatUser selectUserInfoByOpenid(String openid) {
        WechatUser wechatUser = baseMapper.selectUserInfoByOpenid(openid);

        return wechatUser;
    }

    @Override
    public String selectOpenidByZiOpenid(String openid) {
        String s = baseMapper.selectOpenidByZiOpenid(openid);
        return s;
    }

    @Override
    public void insertOrUpdateRelation(String fuid, String ziid) {
        //说明存在，存在就用update
        if(baseMapper.relationIsExist(ziid)!=0){
            baseMapper.updateRelation(fuid,ziid);
        }else {
            baseMapper.insertRelation(fuid,ziid);
        }
    }

    @Override
    public boolean isRelation(String fuid, String ziid) {
        if (baseMapper.isRelation(fuid,ziid)!=0){
            System.out.println("已经存在父子关系");
            return true;
        }
        return false;
    }

    public void insertRelation(String fuid, String ziid) {
        baseMapper.insertRelation(fuid,ziid);
    }
}
