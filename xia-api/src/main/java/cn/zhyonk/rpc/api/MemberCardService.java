package cn.zhyonk.rpc.api;

import cn.zhyonk.entity.MemberCard;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 会员卡表 服务类
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-27
 */
public interface MemberCardService extends IService<MemberCard> {

	List<MemberCard> selectMemberCardList(String openid);

}
