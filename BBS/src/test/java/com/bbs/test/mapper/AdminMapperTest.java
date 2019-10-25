package com.bbs.test.mapper;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.mapper.AdminMapper;
import com.bbs.pojo.Admin;

/**
 * @author 杨锐光
 * 管理员Mapper测试类
 */
public class AdminMapperTest {

	ApplicationContext ctx;
	@Before
	public void init() {
		 ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	@Test
	public void getalladmin() {
	AdminMapper am =(AdminMapper) ctx.getBean("adminMapper");
	List<Admin> admin = am.getAllAdmin();
	for(Admin a:admin) {
		System.out.println(a.getAdminName()+";"+a.getPassword()+";"+a.getId()+";"+a.getState());
	}
		
	}
}
