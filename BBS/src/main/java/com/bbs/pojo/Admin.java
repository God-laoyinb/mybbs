 package com.bbs.pojo;

/**
 * @author 郭荣锋
 * 管理员实体类
 */
public class Admin {
	
	private Integer id; //编号
	private String adminName; //管理员名称
	private String password; //密码
	private Integer state; //状态
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
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
