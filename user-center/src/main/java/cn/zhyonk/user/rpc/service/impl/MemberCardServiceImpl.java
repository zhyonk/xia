package cn.zhyonk.user.rpc.service.impl;

import cn.zhyonk.entity.MemberCard;
import cn.zhyonk.rpc.api.MemberCardService;
import cn.zhyonk.user.mapper.MemberCardMapper;
import cn.zhyonk.user.mapper.ShopMapper;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员卡表 服务实现类
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-27
 */
@Service
public class MemberCardServiceImpl extends ServiceImpl<MemberCardMapper, MemberCard> implements MemberCardService {
	
	@Autowired
	private MemberCardMapper memberCardMapper;
	
	@Override
	public List<MemberCard> selectMemberCardList(String openid) {
		List<MemberCard> list = memberCardMapper.selectMemberCardList(openid);
		return list;
	}

}
