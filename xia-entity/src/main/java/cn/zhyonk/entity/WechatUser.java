package cn.zhyonk.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
 
@TableName(value = "local_user")
public class WechatUser implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId
    private java.lang.String openId;
    private java.lang.Boolean subscribe;
    private java.lang.String nickname;
    private Integer sex;
    private java.lang.String language;
    private java.lang.String city;
    private java.lang.String province;
    private java.lang.String country;
    private java.lang.String headImgUrl;
    private java.lang.Long subscribeTime;
    private java.lang.String unionId;
    private java.lang.Integer sexId;
    private java.lang.String remark;
    private java.lang.Integer groupId;
    @TableField(exist = false)
    private java.lang.String tagIds;

    private java.lang.Integer isDel;

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getSexId() {
        return sexId;
    }

    public void setSexId(Integer sexId) {
        this.sexId = sexId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public WechatUser cast(WxMpUser user){
        this.city = user.getCity();
        this.country = user.getCountry();
        this.groupId = user.getGroupId();
        this.headImgUrl = user.getHeadImgUrl();
        this.language = user.getLanguage();
        this.nickname = user.getNickname();
        this.openId = user.getOpenId();
        this.province = user.getProvince();
        this.remark = user.getRemark();
        this.sex = user.getSex();
        this.subscribe = user.getSubscribe();
        this.unionId = user.getUnionId();
        this.subscribeTime = user.getSubscribeTime();
        this.tagIds = StringUtils.join(user.getTagIds());
        return this;
    }
}
