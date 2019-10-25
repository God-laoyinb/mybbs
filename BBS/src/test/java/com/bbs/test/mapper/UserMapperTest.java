package com.bbs.test.mapper;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.exception.UserExistException;
import com.bbs.mapper.UserMapper;
import com.bbs.pojo.User;

/**
 * @author 刘言
 * 用户Mapper测试类
 */
public class UserMapperTest {
	
	@Test
	public void insert() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper bean = (UserMapper) context.getBean("userMapper");
		User user =new User();
		user.setUserName("aaa");
		user.setPassword("1234456");
		user.setState(1);
		try {
			bean.insetUser(user);
		} catch (UserExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(user.getuId());
		
	}
	@Test
	public void getUserByName() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper bean = (UserMapper) context.getBean("userMapper");
		User user = bean.getUserByName("aaa");
		System.out.println(user);
	}
}	
