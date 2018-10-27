package cn.zhyonk.user.mapper;

import cn.zhyonk.common.SuperMapper;
import cn.zhyonk.entity.Banner;

import java.util.Set;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-25
 */
public interface BannerMapper extends SuperMapper<Banner> {

	Set<Banner> getBannerList();
	
}
