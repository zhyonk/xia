package cn.zhyonk.user.rpc.service.impl;

import cn.zhyonk.entity.Shop;
import cn.zhyonk.rpc.api.ShopService;
import cn.zhyonk.user.mapper.ShopMapper;
import cn.zhyonk.user.mapper.UserMapper;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

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
	
	@Override
	public Shop selectShopDetailById(Integer shopId) {
		Shop shop = shopMapper.selectShopDetailById(shopId);
		return shop;
	}

	
}
