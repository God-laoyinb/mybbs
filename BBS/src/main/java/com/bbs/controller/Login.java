package com.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.pojo.User;
import com.bbs.service.AdminService;
import com.bbs.service.UserService;

/**
 * @author 
 * 注册页面控制器
 */
@Controller
@RequestMapping("/Login")
public class Login {

	@Resource(name="mv")
	private ModelAndView mv;
	
	@Resource(name="userServiceImpl")
	private UserService us;

	@Resource(name="adminServiceImpl")
	private AdminService as;
	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
		mv.addObject("prePage",request.getAttribute("nowPage")); //设置前一页 
		mv.setViewName("login");
		return mv;
	}
	
	/**
	 * 验证用户身份
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/validate")
	public void validate(HttpServletRequest request,HttpServletResponse response) throws IOException { 
		PrintWriter printWriter = response.getWriter();
		Integer status = Integer.parseInt(request.getParameter("status"));
		String userName = request.getParameter("userName"); //用户
		String password = request.getParameter("password"); //密码
		
		//调用服务层验证用户身份
		//用户是否存在
		if(null == us.getUserIdByName(userName)) {
			printWriter.write("{\"res\":\"0\"}");
			printWriter.close();
			return;
		}
		//验证密码是否正确
		try {
			boolean b = us.validUser(userName, password);
			if(b==true) {
				User user = us.getUserByName(userName);
				request.getSession().setAttribute("user", user);
				printWriter.write("{\"res\":\"1\"}");
				printWriter.close();
			}
			else {
				printWriter.write("{\"res\":\"2\"}");
				printWriter.close();
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
	
	/**
	 * 验证管理员身份
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/adminValidate")
	public void adminValidate(HttpServletRequest request,HttpServletResponse response) throws IOException { 
		PrintWriter printWriter = response.getWriter();
		Integer status = Integer.parseInt(request.getParameter("status"));
		String adminName = request.getParameter("userName"); //用户
		String password = request.getParameter("password"); //密码
		
		//调用服务层验证用户身份
				//用户是否存在
				if(null == as.getAdminIdByName(adminName)) {
					printWriter.write("{\"res\":\"0\"}");
					printWriter.close();
					return;
				}
				//验证密码是否正确
				try {
					boolean b = as.validadmin(adminName, password);
					if(b==true) {
						Integer id = as.getAdminIdByName(adminName);
						System.out.println(id);
						request.getSession().setAttribute("adminId", id);
						printWriter.write("{\"res\":\"1\"}");
						printWriter.close();
					}
					else {
						printWriter.write("{\"res\":\"2\"}");
						printWriter.close();
					}
				} catch (Exception e) {
					throw new RuntimeException();
				}
	}
	
	/**
	 * 验证用户登录状态
	 * @throws IOException 
	 * 
	 */
	@RequestMapping("/validateLogin")
	public void validateLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		//Integer userId = 1; //test
		User user= (User)request.getSession().getAttribute("user");
		if(null == user) {
			printWriter.write("{\"res\":\"0\"}");
		} else {
			printWriter.write("{\"res\":\"1\"}");
		}
		printWriter.close();
	}
	@RequestMapping("/validateAdminLogin")
	public void validateAdminLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		//Integer userId = 1; //test
		Integer adminid = (Integer) request.getSession().getAttribute("adminId");
		if(null == adminid) {
			printWriter.write("{\"res\":\"0\"}");
		} else {
			printWriter.write("{\"res\":\"1\"}");
		}
		printWriter.close();
	}
	/**
	 * 用户退出
	 * @param request
	 * @param response
	 */
	@RequestMapping("/userlogout")
	public String userlogout(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		return "redirect:/Login/login";
	}
	/**
	 * 管理员退出
	 * @param request
	 * @param response
	 */
	@RequestMapping("/adminlogout")
	public String adminlogout(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute("adminId");
		return "redirect:/Login/login";
	}
}
