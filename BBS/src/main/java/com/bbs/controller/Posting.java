package com.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.pojo.Block;
import com.bbs.pojo.Post;
import com.bbs.service.BlockService;
import com.bbs.service.PostService;
import com.bbs.service.UserService;

/**
 * @author 
 * 发帖控制器
 */
@Controller
@RequestMapping("/Posting")
public class Posting {
	
	@Resource(name="mv")
	private ModelAndView mv;
	@Resource(name="blockServiceImpl")
	private BlockService blockService;
	@Resource(name="postServiceImpl")
	private PostService postService;
	@Resource(name="post")
	private Post post;
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	/**
	 * 发帖页面
	 * @return
	 */
	@RequestMapping("/posting")
	public ModelAndView posting(HttpServletRequest request,HttpServletResponse response) {
		List<Block> blockList = blockService.getAllBlock(); //获取所有版块
		mv.addObject("blockList",blockList); //添加版块列表
		mv.setViewName("posting");
		return mv;
	}
	
	/**
	 * 上载图片
	 * @param request
	 * @param response
	 */
	@RequestMapping("/uploader")
	public void upload(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("收到图片");
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest Murequest = resolver.resolveMultipart(request);
        Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
        String uploadUrl = request.getSession().getServletContext().getRealPath("/")+"static\\images\\";//得到当前工程路径拼接上文件名
        File dir = new File(uploadUrl);
        int counter = 0;
        String fileName = null;
        File tagetFile;
        System.out.println(uploadUrl);
        if(!dir.exists())//目录不存在则创建
            dir.mkdirs();
        for(MultipartFile file :files.values()) {
            counter++;
            fileName=file.getOriginalFilename();
            tagetFile = new File(uploadUrl+fileName);//创建文件对象
            if(!tagetFile.exists()) {//文件名不存在 则新建文件，并将文件复制到新建文件中
                try {
                    tagetFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    file.transferTo(tagetFile);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("接收完毕");
	}
	
	/**
	 * 添加帖子
	 * @throws IOException 
	 */
	@RequestMapping("/add")
	public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		String blockName = request.getParameter("blockName");
		String title = request.getParameter("title");
		String content = request.getParameter("content"); //内容
		Integer userId = 1; //test
		String imgAddress = request.getParameter("imgAddress");
		post.setImgAddress(imgAddress);
		post.setContent(content);
		post.setTitle(title);
		post.setBlock(blockService.getBlockByName(blockName));
		post.setUser(userService.getUserById(userId));
		postService.addPost(post);
		printWriter.write("{\"res\":\"1\"}");
		printWriter.close();
	}
	
}
