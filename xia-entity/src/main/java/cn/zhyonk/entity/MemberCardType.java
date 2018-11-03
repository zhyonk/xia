package cn.zhyonk.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员卡类型表
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-27
 */
@TableName("member_card_type")
public class MemberCardType extends Model<MemberCardType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "member_card_type_id", type = IdType.AUTO)
    private Long memberCardTypeId;
    @TableField("member_card_type_name")
    private String memberCardTypeName;
    /**
     * 最小单位分
     */
    @TableField("member_type_price")
    private Integer memberTypePrice;
    @TableField("member_card_type_color")
    private String memberCardTypeColor;
    @TableField("valid_time")
    private Long validTime;
    /**
     * 折扣
     */
    private String discounts;
    /**
     * 是否永久 0否 1是
     */
    @TableField("is_forever")
    private Integer isForever;
    @TableField("create_tame")
    private Long createTame;
    @TableField("update_time")
    private Long updateTime;
    @TableField("is_del")
    private Integer isDel;


    public Long getMemberCardTypeId() {
        return memberCardTypeId;
    }

    public void setMemberCardTypeId(Long memberCardTypeId) {
        this.memberCardTypeId = memberCardTypeId;
    }

    public String getMemberCardTypeName() {
        return memberCardTypeName;
    }

    public void setMemberCardTypeName(String memberCardTypeName) {
        this.memberCardTypeName = memberCardTypeName;
    }

    public Integer getMemberTypePrice() {
        return memberTypePrice;
    }

    public void setMemberTypePrice(Integer memberTypePrice) {
        this.memberTypePrice = memberTypePrice;
    }

    public String getMemberCardTypeColor() {
        return memberCardTypeColor;
    }

    public void setMemberCardTypeColor(String memberCardTypeColor) {
        this.memberCardTypeColor = memberCardTypeColor;
    }

    public Long getValidTime() {
        return validTime;
    }

    public void setValidTime(Long validTime) {
        this.validTime = validTime;
    }

    public String getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }

    public Integer getIsForever() {
        return isForever;
    }

    public void setIsForever(Integer isForever) {
        this.isForever = isForever;
    }

    public Long getCreateTame() {
        return createTame;
    }

    public void setCreateTame(Long createTame) {
        this.createTame = createTame;
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
        return this.memberCardTypeId;
    }

    @Override
    public String toString() {
        return "MemberCardType{" +
        ", memberCardTypeId=" + memberCardTypeId +
        ", memberCardTypeName=" + memberCardTypeName +
        ", memberTypePrice=" + memberTypePrice +
        ", memberCardTypeColor=" + memberCardTypeColor +
        ", validTime=" + validTime +
        ", discounts=" + discounts +
        ", isForever=" + isForever +
        ", createTame=" + createTame +
        ", updateTime=" + updateTime +
        ", isDel=" + isDel +
        "}";
    }
}
