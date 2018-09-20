package cn.zhyonk.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 
 * </p>
 *
 * @author zy
 * @since 2018-07-27
 */
public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long role_id;
    private Long per_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getPer_id() {
        return per_id;
    }

    public void setPer_id(Long per_id) {
        this.per_id = per_id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
        "id=" + id +
        ", role_id=" + role_id +
        ", per_id=" + per_id +
        "}";
    }
}
