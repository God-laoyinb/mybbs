package com.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.pojo.Block;
import com.bbs.pojo.Paging;
import com.bbs.pojo.Post;
import com.bbs.service.BlockService;
import com.bbs.service.PostService;
import com.google.gson.Gson;

/**
 * @author 
 * 帖子管理控制器
 */
@Controller
@RequestMapping("/PostManage")
public class PostManage {

	@Resource(name="mv")
	private ModelAndView mv;
	@Resource(name="postServiceImpl")
	private PostService postService;
	@Resource(name="gson")
	private Gson gson;
	@Resource(name="blockServiceImpl")
	private BlockService blockService;
	
	/**
	 * 帖子列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/post-list")
	public ModelAndView postList(HttpServletRequest request,HttpServletResponse response) {
		
		List<Block> blockList = blockService.getAllBlock(); //获取所有版块
		mv.addObject("blockList",blockList); //添加版块列表
		mv.setViewName("admin/post-list");
		return mv;
	}
 
	/**
	 * 以分页的形式获取帖子列表
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
		System.out.println("blockName="+blockName);
		Map map = new HashMap();
		map.put("page",page);
		map.put("pageSize",pageSize);
		if(null != blockName && !blockName.equals("")) { //如果是按版块名称查询帖子
			map.put("blockName",blockName);
			paging = postService.getPostByPaging(map);
			
		} else {
			paging = postService.getPostAllByPaging(map);
		}
		String s = gson.toJson(paging);
		printWriter.write(s);
		printWriter.close();
	}
	
	
	/**
	 * 更新学生信息页面 
	 * @return
	 */
	@RequestMapping("/post-update")
	public ModelAndView update_post(HttpServletRequest request,HttpServletResponse response) {
		Integer postId = Integer.parseInt(request.getParameter("postId"));
		Post post = postService.getPostById(postId);
		List<Block> blockList = blockService.getAllBlock(); //获取所有版块
		mv.addObject("postTime",post.getPostTime());
		mv.addObject("blockList",blockList); //添加版块列表
		mv.addObject("blockValue",post.getBlock().getbId()); //帖子对应的版块在数据库中的顺序
		mv.addObject("userName",post.getUser().getUserName()); //发帖人
		mv.addObject("content",post.getContent()); //内容
		mv.addObject("title",post.getTitle()); //主题
		mv.addObject("postId",postId);
		mv.setViewName("admin/post-update");
		return mv;
	}
	
	/**
	 * 更新学生信息
	 * @throws IOException 
	 */
	@RequestMapping("/updatePost")
	public void updatePost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter(); 
		Integer postId = Integer.parseInt(request.getParameter("postId"));
		String content = request.getParameter("content"); //帖子内容
		String userName = request.getParameter("userName"); //用户名
		String blockName = request.getParameter("blockName"); //版块名称
		String title = request.getParameter("title"); //主题
		Integer isHot = Integer.parseInt(request.getParameter("hot")); //是否热门
		Map map = new HashMap();
		map.put("userName",userName);
		map.put("content",content);
		map.put("title",title);
		map.put("blockName",blockName);
		map.put("isHot",isHot);
		map.put("postId",postId);
		postService.updatePost(map);
		printWriter.write("{\"res\":\"1\"}");
		printWriter.close();
	}
	
	/**
	 * 删除帖子
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/deletePost")
	public void deletePost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		Integer postId = Integer.parseInt(request.getParameter("postId"));
		postService.deletePost(postId);
		printWriter.write("{\"res\":\"1\"}");
		printWriter.close();
	}
	
	/**
	 * 批量删除帖子
	 * @throws IOException 
	 */
	@RequestMapping("/deletePostByQuery")
	public void deletePostByQuery(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		String list = request.getParameter("postIdList");
		String[] list1 = gson.fromJson(list,String[].class); //获取要删除的学生编号
		for(String postId:list1) { //循环遍历删除
			postService.deletePost(Integer.parseInt(postId));
		}
		printWriter.write("{\"res\":\"1\"}");
		printWriter.close();
	}
	
}