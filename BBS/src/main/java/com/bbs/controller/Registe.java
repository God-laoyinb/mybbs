package com.bbs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 
 * 注册页面控制器
 */
@Controller
@RequestMapping("/Registe")
public class Registe {

	@Resource(name="mv")
	private ModelAndView mv;
	
	/**
	 * 注册页面
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView registe(HttpServletRequest request,HttpServletResponse response) {
		mv.setViewName("registe");
		return mv;
	}

}
