package com.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.pojo.User;
import com.bbs.service.UserService;

@Controller
@RequestMapping("/Regist")
public class Regist {
	@Resource(name="mv")
	private ModelAndView mv;
	@Resource(name="userServiceImpl")
	private UserService us;
	
	/**
	 * 发送注册页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/regist")
	public ModelAndView regist(HttpServletRequest request,HttpServletResponse response) {
		mv.setViewName("registe");
		return mv;
	}
	/**
	 * 进行注册
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/registform")
	public void registform(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
		PrintWriter printWriter = response.getWriter();
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		if(us.getUserIdByName(username)!=null) {
			printWriter.write("{\"res\":\"0\"}");
			printWriter.close();
			return;
		}
		try {
			User user= new User();
			user.setUserName(username);
			user.setPassword(password);
			user.setState(1);
			us.addUser(user);
			printWriter.write("{\"res\":\"1\"}");
			printWriter.close();
		} catch (Exception e) {
			printWriter.write("{\"res\":\"2\"}");
			printWriter.close();
		}
		
	}
}
