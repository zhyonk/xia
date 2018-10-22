package cn.zhyonk.user.mapper;

import cn.zhyonk.common.SuperMapper;
import cn.zhyonk.entity.Shop;

import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 商家表 Mapper 接口
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-22
 */
public interface ShopMapper extends SuperMapper<Shop> {

	Shop selectShopDetailById(@Param(value = "shopid") Integer shopid);
	
}
