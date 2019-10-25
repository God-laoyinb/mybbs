package com.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/PersonalCenter")
public class PersonalCenter {

	@Resource(name="mv")
	private ModelAndView mv;
	
	@RequestMapping("/personalCenter")
	public ModelAndView personalCenter(HttpServletRequest request,HttpServletResponse response) {
		mv.setViewName("personalCenter");
		return mv;
	}
	
	/**
	 *通过不同条件获取帖子列表  
	 * @throws IOException 
	 */
	@RequestMapping("/getPost")
	public void getPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		
		
		printWriter.close();
	}
	
}
