package cn.zhyonk.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.zhyonk.common.SuperMapper;
import cn.zhyonk.entity.MemberCard;
import cn.zhyonk.entity.MemberCardInfo;

/**
 * <p>
 * 会员卡表 Mapper 接口
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-27
 */
public interface MemberCardMapper extends BaseMapper<MemberCard> {

	
	List<MemberCard> selectMemberCardList(@Param(value = "openid") String openid);

	MemberCardInfo getMemberCardByNumber(@Param(value = "number") String number);

}
