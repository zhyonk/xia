package cn.zhyonk.rpc.api;

import cn.zhyonk.entity.Banner;
import cn.zhyonk.entity.IndexUserCardInfo;
import cn.zhyonk.entity.Shop;
import cn.zhyonk.entity.UserTag;

import java.util.Set;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商家表 服务类
 * </p>
 *
 * @author zhyonk
 * @since 2018-10-22
 */
public interface ShopService extends IService<Shop> {

	Shop selectShopDetailById(Integer shopId);

	Set<Banner> getBannerList();

	Set<IndexUserCardInfo> getWorkUserList();
	
	UserTag getTagById(String id);
}
