package com.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bbs.mapper.ReplyMapper;
import com.bbs.pojo.Paging;
import com.bbs.pojo.Reply;
import com.bbs.service.ReplyService;
import com.bbs.util.DateUtil;

/**
 * @author 
 * 回复服务层
 */
@Service("replyServiceImpl")
public class ReplyServiceImpl implements ReplyService {

	@Resource(name="paging")
	private Paging paging;
	@Resource(name="replyMapper")
	private ReplyMapper replyMapper;
	
	/**
	 * 以分页的形式通过帖子编号获取回复
	 * @param map page 当前页  pageSize 页面大小 postId 帖子编号
	 * @return 
	 */
	public Paging<Reply> getReplyByPostId(Map map) {
		Integer currentPage = (Integer)map.get("page"); //当前页
		Integer pageSize = (Integer)map.get("pageSize"); //页面大小
		Integer postId = (Integer)map.get("postId");
		Map map1 = new HashMap();
		map1.put("start",(currentPage-1)*pageSize);
		map1.put("offset",pageSize);
		map1.put("postId",postId);
		List<Reply> replyList = replyMapper.getReplyByPostId(map1);
		paging.setList(replyList);
		paging.setCurrentPage(currentPage);
		paging.setPageSize(pageSize);
		paging.setTotalCount(replyMapper.getReplyTotalCountByPostId(postId));//获取总记录数
		return paging;
	}

	/**
	 * 添加回复
	 * @param postId 回复的帖子编号
	 * @param userId 用户编号
	 * @param content 回复内容
	 * @return void
	 */
	public void addReply(Integer userId, Integer postId, String content) {
		Map map = new HashMap();
		map.put("postId",postId);
		map.put("userId",userId);
		map.put("content",content);
		map.put("replyTime",DateUtil.getNowDateForSql());
		replyMapper.addReply(map);
	}
	
}
