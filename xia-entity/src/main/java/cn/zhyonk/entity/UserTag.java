package cn.zhyonk.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 工作人员标签表
 * </p>
 *
 * @author Yanghu
 * @since 2018-10-27
 */
@TableName("user_tag")
public class UserTag extends Model<UserTag> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "user_tag_id", type = IdType.AUTO)
    private Long userTagId;
    /**
     * 标签名称
     */
    @TableField("tag_name")
    private String tagName;
    @TableField("create_time")
    private Long createTime;
    @TableField("update_time")
    private Long updateTime;
    @TableField("is_del")
    private Integer isDel;


    public Long getUserTagId() {
        return userTagId;
    }

    public void setUserTagId(Long userTagId) {
        this.userTagId = userTagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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
        return this.userTagId;
    }

    @Override
    public String toString() {
        return "UserTag{" +
        ", userTagId=" + userTagId +
        ", tagName=" + tagName +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", isDel=" + isDel +
        "}";
    }
}
