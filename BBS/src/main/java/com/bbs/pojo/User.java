package com.bbs.pojo;

/**
 * @author 杨锐光
 * 用户实体类
 */
public class User {
	
	private Integer uId; //编号
	private String userName; //用户名
	private String password; //密码
	private Integer state; //状态
	
	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
