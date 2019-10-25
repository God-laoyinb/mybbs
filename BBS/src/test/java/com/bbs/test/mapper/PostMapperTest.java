package com.bbs.test.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.mapper.BlockMapper;
import com.bbs.mapper.PostMapper;
import com.bbs.mapper.UserMapper;
import com.bbs.pojo.Block;
import com.bbs.pojo.Post;
import com.bbs.pojo.User;

/**
 * @author 杨锐光
 * 帖子Mapper测试类
 */
public class PostMapperTest {
	
	/**
	 * 通过关键词获取帖子列表
	 */
	@Test
	public void getPostByKeyword() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostMapper postMapper = ctx.getBean("postMapper",PostMapper.class);
		Map<Object, Object> map = new HashMap();
		map.put("start",0);
		map.put("offset",1);
		map.put("keyword","java");
		List<Post> list = postMapper.getPostByKeyword(map);
		for (Post p:list) {
			System.out.println(p.getContent());
		}
	}
	
	/**
	 * 通过版块编号获取帖子对象
	 */
	@Ignore
	public void getPostByBlockId() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostMapper postMapper = ctx.getBean("postMapper",PostMapper.class);
//		List<Post> list = postMapper.getPostByBlockId(1);
//		for (Post p:list) {
//			System.out.println(p.getContent());
//		}
	}
	
	/**
	   *   测试获取帖子回复数
	 */
	@Ignore
	public void getPostById() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostMapper postMapper = ctx.getBean("postMapper",PostMapper.class);
		Post post = postMapper.getPostById(1);
		//System.out.println(post.getReplyNum());
		System.out.println(post.getUser().getUserName());
	}
	
	/**
	 * 测试获取最后num条帖子
	 */
	@Ignore
	public void getLastByNum() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostMapper postMapper = ctx.getBean("postMapper",PostMapper.class);
		List<Post> list = postMapper.getLastPostByNum(5);	
		for (Post p:list) {
			System.out.println(p.getContent());
		}
	}
	
	/**
	 * 测试获取热门帖子
	 */
	@Ignore
	public void getHotPostByNum() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostMapper postMapper = ctx.getBean("postMapper",PostMapper.class);
		List<Post> list = postMapper.getHotPostByNum(5);	
		for (Post p:list) {
			System.out.println(p.getContent());
		}
	}
	
	/**
	 * 测试以分页的形式获取全部帖子
	 */
	@Ignore
	public void getAllPostByPaging() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostMapper postMapper = ctx.getBean("postMapper",PostMapper.class);
		Map map = new HashMap();
		map.put("start",0);
		map.put("offset",10);
		List<Post> list = postMapper.getAllPostByPaging(map);
		for (Post p:list) {
			System.out.println(p.getContent());
		}
	}
	
	/**
	 * 测试添加帖子
	 */
	@Ignore
	public void addPost() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostMapper postMapper = ctx.getBean("postMapper",PostMapper.class);
		BlockMapper blockMapper = ctx.getBean("blockMapper",BlockMapper.class);
		UserMapper userMapper = ctx.getBean("userMapper",UserMapper.class);
		Post post = ctx.getBean("post",Post.class);
		Block block = blockMapper.getBlockById(1);
		User user = userMapper.getUserById(1);
		post.setTitle("test");
		post.setUser(user);
		post.setContent("test");
		post.setBlock(block);
		postMapper.addPost(post);
	}
	
	/**
	 * 测试通过不同条件获取帖子
	 */
	@Test
	public void getPost() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostMapper postMapper = ctx.getBean("postMapper",PostMapper.class);
		Map map = new HashMap();
		map.put("userId",1);
		List<Post> list = postMapper.getPost(map);
		for (Post p:list) {
			System.out.println(p.getpId());
		}
	}
	
}
