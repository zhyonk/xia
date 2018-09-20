package cn.zhyonk.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;

import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zy
 * @since 2018-07-27
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long user_id;	
    /**
     * 微信open_id
     */
    private String open_id;
    /**
     * 用户电话-账号
     */
    private String user_phone;
    /**
     * 0 会员 1员工
     */
    private Integer role_id;
    /**
     * 用户名称
     */
    private String user_name;
    /**
     * 性别 0女 1男
     */
    private Integer sex;
    /**
     * 用户状态 0正常 1锁定
     */
    private Integer status;
    private Long is_authorize;
    /**
     * 职务Id
     */
    private Integer position_id;
    /**
     * 标签列表
     */
    private String tag_list;
    /**
     * 好评率
     */
    private String praise_average;
    private Long create_time;
    private Long update_time;
    private Integer is_del;
    /**
     * 密码
     */
    private String password;

    @TableField(exist = false)
    private List<Role> roleList;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getIs_authorize() {
        return is_authorize;
    }

    public void setIs_authorize(Long is_authorize) {
        this.is_authorize = is_authorize;
    }

    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public String getTag_list() {
        return tag_list;
    }

    public void setTag_list(String tag_list) {
        this.tag_list = tag_list;
    }

    public String getPraise_average() {
        return praise_average;
    }

    public void setPraise_average(String praise_average) {
        this.praise_average = praise_average;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    protected Serializable pkVal() {
        return this.user_id;
    }

    @Override
    public String toString() {
        return "User{" +
        "user_id=" + user_id +
        ", open_id=" + open_id +
        ", user_phone=" + user_phone +
        ", role_id=" + role_id +
        ", user_name=" + user_name +
        ", sex=" + sex +
        ", status=" + status +
        ", is_authorize=" + is_authorize +
        ", position_id=" + position_id +
        ", tag_list=" + tag_list +
        ", praise_average=" + praise_average +
        ", create_time=" + create_time +
        ", update_time=" + update_time +
        ", is_del=" + is_del +
        ", password=" + password +
        "}";
    }
}
