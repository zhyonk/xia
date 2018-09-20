package cn.zhyonk.entity;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationToken;


public class RedisLogin implements AuthenticationToken,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8116817810829835862L;
 
	/**
	 * 用户id
	 */
	private String uid;
	
	/**
	 * jwt生成的token信息
	 */
	private String token;
	
	/**
	 * 登录或刷新应用的时间
	 */
	private long refTime;
	
	public RedisLogin(){
		
	}
	
	public RedisLogin(String uid, String token, long refTime){
		this.uid = uid;
		this.token = token;
		this.refTime = refTime;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getRefTime() {
		return refTime;
	}
	public void setRefTime(long refTime) {
		this.refTime = refTime;
	}

	@Override
	public Object getPrincipal() {
		return this.uid;
	}

	@Override
	public Object getCredentials() {
		return Boolean.TRUE;
	}
}
