package cn.zhyonk.entity;

import java.io.Serializable;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationToken;



public class Login implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1899232511233819216L;
	
	/**
	 * 用户id
	 */
	private String uid;
	
	/**
	 * 登录用户名
	 */
	private String phone;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 用户roleid
	 */
	private Set<String> roles;
	
	public Login(){
		super();
	}
	
	public Login(String uid, String phone, String password,Set<String> roles){
		this.uid = uid;
		this.phone = phone;
		this.password = password;
		this.roles = roles;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}