package com.bbs.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.exception.AdminNonExistException;
import com.bbs.exception.PasswordErrorException;
import com.bbs.pojo.Admin;
import com.bbs.service.AdminService;

/**
 * @author 管理员服务层测试类
 */
public class AdminServiceTest {

	/**
	 * 测试验证管理员身份
	 * 
	 * @throws Exception
	 */
	@Ignore
	public void validate() throws Exception { // 通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminService adminService = ctx.getBean("adminServiceImpl", AdminService.class);
		try {
			adminService.validate("1", "1");
		} catch (AdminNonExistException e1) {
			System.out.println("管理员不存在");
		} catch (PasswordErrorException e2) {
			System.out.println("密码错误");
		}
	}

	@Test
	public void getadminbyname() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminService adminService = ctx.getBean("adminServiceImpl", AdminService.class);
		Integer integer = adminService.getAdminIdByName("1");
		System.out.println(integer);
	}

	@Test
	public void valid() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminService adminService = ctx.getBean("adminServiceImpl", AdminService.class);
		boolean b = adminService.validadmin("2", "123456");
		System.out.println(b);
	}

	@Test
	public void getalladmin() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminService adminService = ctx.getBean("adminServiceImpl", AdminService.class);
		List<Admin> list = adminService.getAllAdmin();
		for (Admin a : list) {
			System.out.println(a.getAdminName() + ";" + a.getPassword() + ";" + a.getId() + ";" + a.getState());
		}
	}

	@Test
	public void stopadmin() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminService adminService = ctx.getBean("adminServiceImpl", AdminService.class);
		adminService.stopAdmin("3");
	}

	@Test
	public void startadmin() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminService adminService = ctx.getBean("adminServiceImpl", AdminService.class);
		adminService.startAdmin("3");
	}

}
