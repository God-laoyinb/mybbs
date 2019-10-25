package com.bbs.test.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.mapper.ReplyMapper;
import com.bbs.pojo.Reply;
import com.bbs.util.DateUtil;

/**
 * @author 刘言
 * 回复Mapper测试类
 */
public class ReplyMapperTest {
	
	/**
	 * 测试以分页形式获取回复列表
	 */
	@Ignore
	public void getReplyByPostId() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ReplyMapper replyMapper = ctx.getBean("replyMapper",ReplyMapper.class);
		Map map = new HashMap();
		map.put("postId",1);
		map.put("start",0);
		map.put("offset",10);
		List<Reply> list = replyMapper.getReplyByPostId(map);
		for (Reply reply:list) {
			//System.out.println(reply.getContent());
			System.out.println(reply.getUser().getUserName());
		}
	}
	
	/**
	 *测试添加回复 
	 */
	@Ignore
	public void addReply() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ReplyMapper replyMapper = ctx.getBean("replyMapper",ReplyMapper.class);
		Map map = new HashMap();
		map.put("postId",1);
		map.put("userId",1);
		map.put("content","testContent");
		map.put("replyTime",DateUtil.getNowDateForSql());
		replyMapper.addReply(map);
	}
	
}
