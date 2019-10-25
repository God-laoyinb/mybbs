package com.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.pojo.Paging;
import com.bbs.pojo.Post;
import com.bbs.service.PostService;
import com.google.gson.Gson;

/**
 * @author 
   *     帖子控制器
 */
@Controller
@RequestMapping("/PostList")
public class PostList {

	@Resource(name="mv")
	private ModelAndView mv;
	@Resource(name="postServiceImpl")
	private PostService postService;
	@Resource(name="gson")
	private Gson gson;
	
	@RequestMapping("/postList")
	public ModelAndView postList(HttpServletRequest request,HttpServletResponse response) {
		
		String blockName = request.getParameter("blockName");
		String keyword = request.getParameter("keyword");
		if(null != blockName) {
			//通过版块名称查询帖子
			mv.addObject("blockName",blockName);
		} else if(null != keyword) { //关键字不为空，根据关键字查询帖子
			mv.addObject("keyword",keyword);
		} else { //随便显示十条帖子
			
		}
		
		mv.setViewName("postList");
		return mv;
	}
	
	/**
	 * 通过分页的形式去获取帖子
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/getPostByPaging")
	public void getPostByPaging(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = response.getWriter();
		Integer page = Integer.parseInt(request.getParameter("page")); 
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize")); 
		Paging<Post> paging = null;
		String blockName = request.getParameter("blockName"); 
		
		if(null != blockName) { //如果是按版块名称查询帖子
			Map map = new HashMap();
			map.put("blockName",blockName);
			map.put("page",page);
			map.put("pageSize",pageSize);
			paging = postService.getPostByPaging(map);
		}
		String s = gson.toJson(paging);
		printWriter.write(s);
		printWriter.close();
	}
	@RequestMapping("/searchPostByPaging")
	public void searchPostByPaging(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter printWriter = response.getWriter();
	    
		Integer page = Integer.parseInt(request.getParameter("page")); 
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize")); 
		Paging<Post> paging = null;
		String keyword = request.getParameter("keyword");
		if(null != keyword) { //如果是按版块名称查询帖子
			Map map = new HashMap();
			map.put("keyword",keyword);
			map.put("page",page);
			map.put("pageSize",pageSize);
			paging = postService.getPostBykeywordPaging(map);
		}
		String s = gson.toJson(paging);
		System.out.println(s);
		printWriter.write(s);
		printWriter.close();
		mv.clear();
		request.setAttribute("keyword", keyword);
		
	}
}
