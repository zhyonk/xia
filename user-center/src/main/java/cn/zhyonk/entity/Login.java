package cn.zhyonk.entity;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationToken;

import net.bytebuddy.asm.Advice.This;

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
	
	public Login(){
		super();
	}
	
	public Login(String uid, String phone, String password){
		this.uid = uid;
		this.phone = phone;
		this.password = password;
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

}