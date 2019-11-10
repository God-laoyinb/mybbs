package com.bbs.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.mapper.PostMapper;
import com.bbs.pojo.Paging;
import com.bbs.pojo.Post;
import com.bbs.service.PostService;

public class PostServiceTest {
	
	@Test
	public void getmypost() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostService postService = ctx.getBean("postServiceImpl",PostService.class);
		Map map1 = new HashMap();
		map1.put("userId",1);
		map1.put("page",1);
		map1.put("pageSize",10);
		 Paging<Post> list = postService.getMyPost(map1);
		 System.out.println(list);
	}
	/**
	 * 测试通过不同类型和数量获取帖子列表
	 */
	@Ignore
	public void getPostByNumAndType() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostService postService = ctx.getBean("postServiceImpl",PostService.class);
		List<Post> list = postService.getPostByNumAndType(5,2);
		for (Post p:list) {
			System.out.println(p.getContent());
		}
	}
	@Test
	public void searchpost() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		PostService postService = ctx.getBean("postServiceImpl",PostService.class);
		Map map1 = new HashMap();
		map1.put("keyword","java");
		map1.put("page",1);
		map1.put("pageSize",10);
		 Paging<Post> list = postService.getPostBykeywordPaging(map1);
		 					
		//for (Post p:list.getList()) {
			System.out.println(list);
		//}
	}
	@Test
	public void search() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostMapper bean = (PostMapper) ctx.getBean("postMapper");
		Map map1 = new HashMap();
		map1.put("keyword","java");
		map1.put("start",1);
		map1.put("offset",10);
		//List<Post> list = bean.getPostByKeyword(map1);
		/*
		 * for (Post p:list) { System.out.println(p.getpId()); }
		 */
		Integer i = bean.getTotalCountByKeyword(map1);
		System.out.println(i);
		
	}
}
