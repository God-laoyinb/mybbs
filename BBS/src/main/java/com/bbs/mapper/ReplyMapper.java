package com.bbs.mapper;

import java.util.List;
import java.util.Map;

import com.bbs.pojo.Reply;

/**
 * @author 陈艺注
 * 回复Mapper
 */
public interface ReplyMapper {

	/**
	 * 根据帖子编号获取回复列表
	 * @param map postId 帖子编号 start 起始位置 offset 偏移量
	 * @return 回复列表
	 */
	public List<Reply> getReplyByPostId(Map map);

	/**
	 * 获取评论总记录数
	 * @param postId 帖子编号
	 * @return 总记录数
	 */
	public Integer getReplyTotalCountByPostId(Integer postId);

	/**
	 * 添加回复
	 * @param map content 回复内容  userId 用户编号  postId  帖子编号    replyTime 回复时间
	 * @return void
	 */
	public void addReply(Map map);
	
}
