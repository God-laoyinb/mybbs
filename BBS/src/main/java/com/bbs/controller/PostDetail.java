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
import com.bbs.service.ReplyService;
import com.google.gson.Gson;

/**
 * @author 
 *帖子详情控制器
 */
@Controller
@RequestMapping("/PostDetail")
public class PostDetail {

	@Resource(name="postServiceImpl")
	private PostService postService;
	@Resource(name="mv")
	private ModelAndView mv;
	@Resource(name="gson")
	private Gson gson;
	@Resource(name="replyServiceImpl")
	private ReplyService replyService;
	
	/**
	 * 帖子详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/postDetail")
	public ModelAndView postDetail(HttpServletRequest request,HttpServletResponse response) {
		Integer postId = Integer.parseInt(request.getParameter("postId"));
		Post post = postService.getPostById(postId);
		mv.addObject("post",post);
		//mv.addObject("replyList",);
		mv.setViewName("postDetail");
		return mv;
	}
	
	/**
	 * 获取回复
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getReply")
	public void getReply(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		Integer postId = Integer.parseInt(request.getParameter("postId"));
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		Map map = new HashMap();
		map.put("pageSize",pageSize);
		map.put("page",page);
		map.put("postId",postId); 
		Paging paging = replyService.getReplyByPostId(map);
		String s = gson.toJson(paging);
		printWriter.write(s);
		printWriter.close();
	}
	
	/**
	 * 添加回复
	 * @throws IOException 
	 */
	@RequestMapping("/addReply")
	public void addReply(HttpServletRequest request,HttpServletResponse response) throws IOException { //添加回复 
		PrintWriter printWriter = response.getWriter();
		Integer userId = 1; //test
		//Integer userId = (Integer)request.getSession().getAttribute("userId"); //当前登录的用户编号
		Integer postId = Integer.parseInt(request.getParameter("postId")); //回复的帖子编号
		String content = request.getParameter("content");
		replyService.addReply(userId, postId, content);
		printWriter.write("{\"res\":\"1\"}");
		printWriter.close();
	}
	
}
