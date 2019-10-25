package com.bbs.pojo;

/**
 * @author 刘言
 * 回复实体类
 */
public class Reply {

	private Integer rId; //编号
	private String content; //回复内容
	private User user; //用户对象
	private Integer state; //状态
	private Integer postId;
	private java.sql.Date replyTime;
	
	public Integer getrId() {
		return rId;
	}

	public void setrId(Integer rId) {
		this.rId = rId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public java.sql.Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(java.sql.Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
