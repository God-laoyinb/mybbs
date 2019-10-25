package com.bbs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 版块管理
 * @author 74578
 *
 */
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/CategoryManage")
public class CategotyManage {
	
	@Resource(name="mv")
	private ModelAndView mv;
	@RequestMapping("/Category-list")
	public ModelAndView CategoryList(HttpServletRequest request,HttpServletResponse response) {
		
		mv.setViewName("admin/category");
		return mv;
		
	}
}
