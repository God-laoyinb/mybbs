package com.bbs.pojo;

import java.util.List;

/**
 * @author 
  *   分页类
 */
public class Paging<T> {
	
	private int currentPage; //当前页
	private int pageSize; //每页显示记录数
	private int totalPage; //总页数
	private int totalCount; //总记录数
	private List<T> list; //每页显示的内容
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		if(0 != totalCount%pageSize) {
			return totalCount/pageSize+1;
		} else {
			return totalCount/pageSize;
		}
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Paging{" + "currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", list=" + list + '}';
	}
}
