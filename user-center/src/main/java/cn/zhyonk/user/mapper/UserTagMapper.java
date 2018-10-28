package cn.zhyonk.user.mapper;

import cn.zhyonk.entity.UserTag;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 工作人员标签表 Mapper 接口
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-27
 */
public interface UserTagMapper extends BaseMapper<UserTag> {

	UserTag getTagById(@Param(value = "id")String id);

}
