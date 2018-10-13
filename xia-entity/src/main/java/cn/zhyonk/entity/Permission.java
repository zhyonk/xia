package cn.zhyonk.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zy
 * @since 2018-07-27
 */
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 权限类型
     */
    private Integer type;
    /**
     * 权限
     */
    private String permission;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 对应url
     */
    private String url;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 前一级id
     */
    private Integer pid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    

    public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

	@Override
	public String toString() {
		return "Permission [id=" + id + ", type=" + type + ", permission=" + permission + ", name=" + name + ", url="
				+ url + ", icon=" + icon + ", sort=" + sort + ", state=" + state + ", pid=" + pid + "]";
	}
}
