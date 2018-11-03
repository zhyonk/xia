package cn.zhyonk.user.rpc.service.impl;

import cn.zhyonk.entity.MemberCardType;
import cn.zhyonk.rpc.api.MemberCardTypeService;
import cn.zhyonk.user.mapper.MemberCardTypeMapper;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员卡类型表 服务实现类
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-27
 */
@Service
public class MemberCardTypeServiceImpl extends ServiceImpl<MemberCardTypeMapper, MemberCardType> implements MemberCardTypeService {

}
