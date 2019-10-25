package com.bbs.pojo;

/**
 * @author 
 * 版块实体类
 */
public class Block {

	private Integer bId;
	private String blockName;
	private Integer state;
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getbId() {
		return bId;
	}
	public void setbId(Integer bId) {
		this.bId = bId;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

}
