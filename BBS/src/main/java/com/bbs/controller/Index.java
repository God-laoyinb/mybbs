	package com.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.pojo.Post;
import com.bbs.service.PostService;
import com.google.gson.Gson;

/**
 * @author 陈艺注
 * index页面控制器
 */
@Controller
@RequestMapping("/Index")
public class Index {
	
	@Resource(name="mv")
	private ModelAndView mv;
	@Resource(name="gson")
	private Gson gson;
	@Resource(name="postServiceImpl")
	private PostService postService;
	
	/**
	 * 首页页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		
		request.getSession().setAttribute("nowPage","Index/index"); //把当前页路径存入session
		mv.setViewName("index");
		return mv;
	}
	
	/**
	 * 通过不同类型获取帖子
	 * @throws IOException 
	 * 
	 */
	@RequestMapping("/getPost")
	public void getPostByNumAndType(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		Integer type = Integer.parseInt(request.getParameter("type")); //类型
		Integer num = Integer.parseInt(request.getParameter("num")); //数量
		List<Post> postList = postService.getPostByNumAndType(num, type);
		String s = gson.toJson(postList);
		printWriter.write(s);
		printWriter.close();
	};
	
}
