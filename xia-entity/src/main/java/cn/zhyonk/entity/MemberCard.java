package cn.zhyonk.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员卡表
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-27
 */
@TableName("member_card")
public class MemberCard extends Model<MemberCard> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "member_card_id", type = IdType.AUTO)
    private Long memberCardId;
    @TableField("member_card_type_id")
    private Long memberCardTypeId;
    @TableField("member_card_type_name")
    private String memberCardTypeName;
    @TableField("member_card_type_color")
    private String memberCardTypeColor;
    @TableField("user_id")
    private Long userId;
    @TableField("member_card_number")
    private String memberCardNumber;
    @TableField("remainder_price")
    private Integer remainderPrice;
    private String discounts;
    @TableField("is_forver")
    private Integer isForver;
    @TableField("create_time")
    private Long createTime;
    private Long deadline;
    /**
     * 状态 0正常 1冻结
     */
    private Integer status;
    /**
     * 是否删除 0否 1是
     */
    @TableField("is_del")
    private Integer isDel;


    public Long getMemberCardId() {
        return memberCardId;
    }

    public void setMemberCardId(Long memberCardId) {
        this.memberCardId = memberCardId;
    }

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

    public String getMemberCardTypeColor() {
        return memberCardTypeColor;
    }

    public void setMemberCardTypeColor(String memberCardTypeColor) {
        this.memberCardTypeColor = memberCardTypeColor;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMemberCardNumber() {
        return memberCardNumber;
    }

    public void setMemberCardNumber(String memberCardNumber) {
        this.memberCardNumber = memberCardNumber;
    }

    public Integer getRemainderPrice() {
        return remainderPrice;
    }

    public void setRemainderPrice(Integer remainderPrice) {
        this.remainderPrice = remainderPrice;
    }

    public String getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }

    public Integer getIsForver() {
        return isForver;
    }

    public void setIsForver(Integer isForver) {
        this.isForver = isForver;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    protected Serializable pkVal() {
        return this.memberCardId;
    }

    @Override
    public String toString() {
        return "MemberCard{" +
        ", memberCardId=" + memberCardId +
        ", memberCardTypeId=" + memberCardTypeId +
        ", memberCardTypeName=" + memberCardTypeName +
        ", memberCardTypeColor=" + memberCardTypeColor +
        ", userId=" + userId +
        ", memberCardNumber=" + memberCardNumber +
        ", remainderPrice=" + remainderPrice +
        ", discounts=" + discounts +
        ", isForver=" + isForver +
        ", createTime=" + createTime +
        ", deadline=" + deadline +
        ", status=" + status +
        ", isDel=" + isDel +
        "}";
    }
}
