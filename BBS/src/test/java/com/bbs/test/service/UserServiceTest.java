package com.bbs.test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.pojo.User;
import com.bbs.service.PostService;
import com.bbs.service.UserService;

public class UserServiceTest {

	
	@Test
	public void insertuser() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService us = ctx.getBean("userServiceImpl",UserService.class);
		User user= new User();
		user.setUserName("bbb");
		user.setPassword("1234456");
		user.setState(1);
		if(us.getUserIdByName("bbb")!=null) {
			System.out.println("aaa");
			return;
		}
		us.addUser(user);
		
	}
	@Test
	public void valid() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService us = ctx.getBean("userServiceImpl",UserService.class);
		boolean b = us.validUser("bbb", "1234456");
		System.out.println(b);
	}
}
