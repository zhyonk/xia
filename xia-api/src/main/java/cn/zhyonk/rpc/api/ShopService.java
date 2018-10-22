package cn.zhyonk.rpc.api;

import cn.zhyonk.entity.Shop;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商家表 服务类
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-22
 */
public interface ShopService extends IService<Shop> {

	Shop selectShopDetailById(Integer shopId);
}
