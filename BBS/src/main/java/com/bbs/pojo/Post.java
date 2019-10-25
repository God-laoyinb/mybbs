package com.bbs.pojo;

/**
 * @author 陈艺注
 * 贴子实体类
 */
public class Post {
	
	private Integer pId; //编号
	private String content; //内容
	private User user; // 发布帖子的用户对象
	private String title; //标题
	private Integer state; //状态
	private Block block; //版块对象
	//private Integer praiseNum; //点赞数	
	private java.sql.Date postTime; //发帖时间 
	private Integer isHot; //是否热门
	private String imgAddress; //图片地址
	private Integer replyNum; //回复数 
	
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
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
	public Integer getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public java.sql.Date getPostTime() {
		return postTime;
	}
	public void setPostTime(java.sql.Date postTime) {
		this.postTime = postTime;
	}
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public Block getBlock() {
		return block;
	}
	public void setBlock(Block block) {
		this.block = block;
	}
	
}
