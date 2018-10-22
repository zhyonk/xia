package cn.zhyonk.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商家表
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-22
 */
public class Shop extends Model<Shop> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "shop_id", type = IdType.AUTO)
    private Long shopId;
    /**
     * 商家名称
     */
    @TableField("shop_name")
    private String shopName;
    /**
     * 商家电话
     */
    @TableField("shop_phone")
    private String shopPhone;
    /**
     * 商家地址详情
     */
    @TableField("shop_address_detail")
    private String shopAddressDetail;
    @TableField("shop_img")
    private String shopImg;
    @TableField("create_time")
    private Long createTime;
    @TableField("update_time")
    private Long updateTime;
    @TableField("is_del")
    private Integer isDel;


    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopAddressDetail() {
        return shopAddressDetail;
    }

    public void setShopAddressDetail(String shopAddressDetail) {
        this.shopAddressDetail = shopAddressDetail;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    protected Serializable pkVal() {
        return this.shopId;
    }

    @Override
    public String toString() {
        return "Shop{" +
        ", shopId=" + shopId +
        ", shopName=" + shopName +
        ", shopPhone=" + shopPhone +
        ", shopAddressDetail=" + shopAddressDetail +
        ", shopImg=" + shopImg +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", isDel=" + isDel +
        "}";
    }
}
