package com.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.bbs.pojo.User;
import com.bbs.service.AdminService;
import com.bbs.service.UserService;

@Controller
@RequestMapping("/UserManage")
public class UserManage {
	
	@Resource(name="mv")
	private ModelAndView mv;
	
	@Resource(name="userServiceImpl")
	private UserService us;
	@Autowired
	User user;
	/**
	 * 用户列表页面
	 * @param
	 * @return
	 */
	@RequestMapping("/user-list")
	public ModelAndView userList(HttpServletRequest request,HttpServletResponse response) {
		
		List<User> userList = us.getAllUser();
		mv.addObject("userList",userList);
		mv.setViewName("admin/user-list");
		return mv;
	}
	/**
	 * 添加用户信息页面
	 * @param
	 * @return
	 */
	@RequestMapping("/user-add")
	public ModelAndView addUser(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
	
		mv.setViewName("admin/user-add");
		return mv;
	}
	/**
	 * 更新用户信息页面
	 * @param
	 * @return
	 */
	@RequestMapping("/user-update")
	public ModelAndView updateUser(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		//填充数据
		//mv.addObject("realName", request.getParameter("realName"));
		mv.addObject("userName",request.getParameter("userName"));
		mv.addObject("balance",request.getParameter("balance"));
		mv.addObject("address",request.getParameter("address"));
		mv.addObject("mobile",request.getParameter("mobile"));
		mv.addObject("password",request.getParameter("password"));
		mv.addObject("userId",request.getParameter("userId"));
		mv.setViewName("admin/user-update");
		return mv;
	}
	
	/**
	 * 删除用户
	 * @param
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/delete")
	public void deleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("com/cxf/pojo/applicationContext.xml");
		PrintWriter printWriter = response.getWriter();
		//UserService userService = (UserService)ctx.getBean("userServiceImpl");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		//User user = userService.getUserByName(userName);
		User user = us.findUserById(userId);
		us.deleteUser(user);
		//userService.findUserById(userId);
		printWriter.write("{\"res\":\"1\"}");
		printWriter.close();
	}
	
	/**
	 * 添加用户
	 * @param
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/add")
	public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("com/cxf/pojo/applicationContext.xml");
		PrintWriter printWriter = response.getWriter();
		//UserService userService = (UserService)ctx.getBean("userServiceImpl");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		//String mobile = request.getParameter("mobile");
		//System.out.println(mobile);
		//String address = request.getParameter("address");
		//System.out.println(address);
		if(null != us.getUserByName(userName)) { // 如果用户名已经存在   
			printWriter.write("{\"res\":\"0\"}");
		} else {
			
			
			user.setPassword(password);
			user.setUserName(userName);
			user.setState(1);
			us.addUser(user);
			printWriter.write("{\"res\":\"1\"}");
		} 
		printWriter.close();
	}
	
	/**
	 * 更新用户信息
	 * @param
	 * @return
	 */
	@RequestMapping("/update")
	public void update(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("com/cxf/pojo/applicationContext.xml");
		PrintWriter printWriter = response.getWriter();
		//UserService userService = (UserService)ctx.getBean("userServiceImpl");
		String userName = request.getParameter("userName"); //修改完的用户名
		String password = request.getParameter("password");
		//String mobile = request.getParameter("mobile");
		//String address = request.getParameter("address");
	//	float balance = Float.parseFloat(request.getParameter("balance"));
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		
		User user = us.findUserById(userId);
		System.out.println("nowName=" + user.getUserName());
		System.out.println("newName=" + userName);
		
			if(null != us.getUserByName(userName)&&
					!userName.equals(user.getUserName())
					) { //用户名已存在
				printWriter.write("{\"res\":\"0\"}");
			} else {
				user.setuId(userId);
				
				user.setPassword(password);
				user.setUserName(userName);
				
				us.updateUser(user);
				printWriter.write("{\"res\":\"1\"}");
			}
		
		printWriter.close();
	}
	/**
	  *  停用User
	 * @param
	 * @return
	 */
	@RequestMapping("/stopUser")
	public void stopAdmin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		PrintWriter printWriter = response.getWriter();
		
		String userName = request.getParameter("userName");
		
			try {
				
				us.stopUser(userName);
				
				printWriter.write("{\"res\":\"1\"}");
			} catch (Exception e) {
				printWriter.write("{\"res\":\"0\"}");
			}
			
		//} else {
			//printWriter.write("{\"res\":\"0\"}");
		//}
		printWriter.close();
	}
	@RequestMapping("/startUser")
	public void startUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		PrintWriter printWriter = response.getWriter();
		String userName = request.getParameter("userName");
		us.startUser(userName);
		printWriter.write("{\"res\":\"1\"}");
		printWriter.close();
	}
}
