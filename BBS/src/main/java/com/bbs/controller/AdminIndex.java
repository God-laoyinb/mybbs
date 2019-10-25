package com.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.bbs.exception.AdminIsStopException;
import com.bbs.exception.AdminNonExistException;
import com.bbs.exception.PasswordErrorException;
import com.bbs.pojo.Admin;
import com.bbs.service.AdminService;
import com.bbs.util.MD5Util;

/**
 * @author 郭荣锋
  *  后台管理导航页
 */
@Controller
@RequestMapping("/AdminIndex")
public class AdminIndex {
	
	@Resource(name="adminServiceImpl")
	private AdminService adminService;
	@Resource(name="mv")
	private ModelAndView mv;
	
	/**
	 * 管理员index页面
	 * @param
	 * @return
	 */
	@RequestMapping("/adminIndex")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		Admin admin = adminService.getAdminById((Integer)request.getSession().getAttribute("adminId"));
		//mv.addObject("adminName",admin.getAdminName());
		mv.setViewName("admin/index");
		return mv;
	}

	/**
	 * 退出系统 	
	 * @param
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute("adminId");//删除当前用户信息
		return "redirect:"+"/login.jsp";
	}
		
}
