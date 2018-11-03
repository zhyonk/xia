package cn.zhyonk.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 会员卡表
 * </p>
 *
 * @author zhyonk
 * @since 2018-10-27
 */
public class MemberCardInfo extends Model<MemberCardInfo> {

    private static final long serialVersionUID = 1L;
    
    @TableField("member_card_type_name")
    private String memberCardTypeName;
    
    @TableField("member_card_type_color")
    private String memberCardTypeColor;
    
    @TableField("member_card_number")
    private String memberCardNumber;
    
    @TableField("member_type_price")
    private String memberTypePrice;
    
    @TableField("remainder_price")
    private Integer remainderPrice;
    
    @TableField("discounts")
    private String discounts;
    
    @TableField("is_forever")
    private Integer isForever;
    
    @TableField("create_time")
    private Long createTime;
    
    @TableField("deadline")
    private Long deadline;
    
    @TableField("user_name")
    private String userName;
    /**
     * 状态 0正常 1冻结
     */
    private Integer status;
    
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
	
	public Integer getIsForever() {
		return isForever;
	}
	public void setIsForever(Integer isForever) {
		this.isForever = isForever;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMemberTypePrice() {
		return memberTypePrice;
	}
	public void setMemberTypePrice(String memberTypePrice) {
		this.memberTypePrice = memberTypePrice;
	}
	
	
	
	
	@Override
	public String toString() {
		return "MemberCardInfo [memberCardTypeName=" + memberCardTypeName + ", memberCardTypeColor="
				+ memberCardTypeColor + ", memberCardNumber=" + memberCardNumber + ", memberTypePrice="
				+ memberTypePrice + ", remainderPrice=" + remainderPrice + ", discounts=" + discounts + ", isForever="
				+ isForever + ", createTime=" + createTime + ", deadline=" + deadline + ", userName=" + userName
				+ ", status=" + status + "]";
	}
	@Override
	protected Serializable pkVal() {
		return this.memberCardTypeName;
	}

}
