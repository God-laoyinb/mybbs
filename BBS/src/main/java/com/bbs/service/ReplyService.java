package com.bbs.service;

import java.util.Map;

import com.bbs.pojo.Paging;
import com.bbs.pojo.Reply;

/**
 * @author 
 * 回复服务层接口
 */
public interface ReplyService {
	
	/**
	 * 以分页的形式通过帖子编号获取回复
	 * @param map page 当前页  pageSize 页面大小 postId 帖子编号
	 * @return 
	 */
	public Paging<Reply> getReplyByPostId(Map map);

	/**
	 * 添加回复
	 * @param postId 回复的帖子编号
	 * @param userId 用户编号
	 * @param content 回复内容
	 * @return void
	 */
	public void addReply(Integer userId,Integer postId,String content);
	
}
