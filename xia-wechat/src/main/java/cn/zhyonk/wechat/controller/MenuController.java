package cn.zhyonk.wechat.controller;



import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.xmlrpc.base.Array;

import cn.zhyonk.common.utils.ResponseData;
import cn.zhyonk.common.utils.StringUtils;
import cn.zhyonk.controller.BaseController;
import cn.zhyonk.entity.Banner;
import cn.zhyonk.entity.IndexUserCardInfo;
import cn.zhyonk.entity.Permission;
import cn.zhyonk.entity.Shop;
import cn.zhyonk.entity.UserTag;
import cn.zhyonk.rpc.api.OauthService;
import cn.zhyonk.rpc.api.ShopService;
import cn.zhyonk.rpc.api.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/menu")
@Api(value="菜单管理")
public class MenuController extends BaseController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private OauthService oauthService;
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value="/getMineMenu")
	@ApiOperation(value = "获取Mine下的菜单")
	public ResponseData getMineMenu(HttpServletRequest request) {
		String openid = (String) SecurityUtils.getSubject().getPrincipal();
		Set<Permission> permissions = userService.getPermission(openid);
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("menu", permissions);
        return responseData;
	}
	@RequestMapping(value="/getShopDetail")
	@ApiOperation(value = "获取商铺详细信息")
	public ResponseData getShopDetail(HttpServletRequest request) {
		Integer shopId = 1;
		Shop shop = shopService.selectShopDetailById(shopId);
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("shopInfo", shop);
        return responseData;
	}
	
	@RequestMapping(value="/getBannerList")
	@ApiOperation(value = "获取Banner信息")
	public ResponseData getBannerList(HttpServletRequest request) {
		Set<Banner> list = shopService.getBannerList();
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("bannerList", list);
        return responseData;
	}
	
	@RequestMapping(value="/getUserCardList")
	@ApiOperation(value = "获取首页设计师卡片信息")
	public ResponseData getUserCardList(HttpServletRequest request) {
		Set<IndexUserCardInfo> list = shopService.getWorkUserList();
		if (list!=null) {
			for (IndexUserCardInfo info : list) {
				ArrayList<UserTag> tagList = new ArrayList<UserTag>();
				String tags = info.getTagList();
				if (!StringUtils.isEmpty(tags)) {
					String[] tagArr = tags.split(",");
					for (String tagId : tagArr) {
						if (tagId!="" && tagId!=null) {
							UserTag tag = shopService.getTagById(tagId);
							if (tag!=null) {
								tagList.add(tag);
							}
						}
					}
					info.setTagNameList(tagList);
				}
			}
		}
		
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("workUserCardList", list);
        return responseData;
	}
}
