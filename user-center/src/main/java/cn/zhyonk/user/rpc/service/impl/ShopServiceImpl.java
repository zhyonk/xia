package cn.zhyonk.user.rpc.service.impl;

import cn.zhyonk.entity.Banner;
import cn.zhyonk.entity.IndexUserCardInfo;
import cn.zhyonk.entity.Shop;
import cn.zhyonk.entity.User;
import cn.zhyonk.entity.UserTag;
import cn.zhyonk.rpc.api.ShopService;
import cn.zhyonk.user.mapper.BannerMapper;
import cn.zhyonk.user.mapper.ShopMapper;
import cn.zhyonk.user.mapper.UserMapper;
import cn.zhyonk.user.mapper.UserTagMapper;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家表 服务实现类
 * </p>
 *
 * @author zhyonk
 * @since 2018-10-22
 */
@Service("shopService")
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

	@Autowired
	private ShopMapper shopMapper;
	
	@Autowired
	private BannerMapper bannerMapper;
	
	@Autowired
	private UserTagMapper userTagMapper;

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public Shop selectShopDetailById(Integer shopId) {
		Shop shop = shopMapper.selectShopDetailById(shopId);
		return shop;
	}
	
	@Override
	public Set<Banner> getBannerList() {
		Set<Banner> list = bannerMapper.getBannerList();
		return list;
	}

	@Override
	public Set<IndexUserCardInfo> getWorkUserList() {
		Set<IndexUserCardInfo> list = userMapper.getWorkUserList();
		return list;
	}

	@Override
	public UserTag getTagById(String id) {
		UserTag tag = userTagMapper.getTagById(id);
		return tag;
	}
	
}
