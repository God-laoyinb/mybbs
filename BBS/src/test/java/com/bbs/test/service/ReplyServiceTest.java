package com.bbs.test.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.bbs.pojo.Paging;
import com.bbs.pojo.Reply;
import com.bbs.service.ReplyService;

/**
 * @author 
 * 回复服务层测试类
 */
public class ReplyServiceTest {

	/**
	 * 测试以分页的形式通过帖子编号获取回复列表
	 */
	@Ignore
	public void getReplyByPostId() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ReplyService replyService = ctx.getBean("replyServiceImpl",ReplyService.class);
		Map map = new HashMap();
		map.put("postId",1);
		map.put("page",1);
		map.put("pageSize",10);
		Paging<Reply> paging = replyService.getReplyByPostId(map);
		for (Reply r:paging.getList()) {
			System.out.println(r.getContent());
		}
	}

}
