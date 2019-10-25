package com.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.exception.BlockIsExistException;
import com.bbs.pojo.Block;
import com.bbs.service.BlockService;
import com.google.gson.Gson;

/**
 * @author 
 * 版块管理控制器
 */
@Controller
@RequestMapping("/BlockManage")
public class BlockManage {

	@Resource(name="gson")
	private Gson gson;
	@Resource(name="mv")
	private ModelAndView mv;
	@Resource(name="blockServiceImpl")
	private BlockService blockService;
	
	/**
	 * 版块列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/block-list")
	public ModelAndView blockList(HttpServletRequest request,HttpServletResponse response) {
		List<Block> list = blockService.getAllBlock();
		String blockList = gson.toJson(list);
		mv.addObject("blockList",blockList);		
		mv.setViewName("admin/block-list");
		return mv;
	}
	
	/**
	 * 添加版块
	 * @throws IOException 
	 */
	@RequestMapping("/addBlock")
	public void addBlock(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		String blockName = request.getParameter("blockName");
		try {
			blockService.addBlock(blockName);
			printWriter.write("{\"res\":\"1\"}");
		} catch (BlockIsExistException e) {
			printWriter.write("{\"res\":\"0\"}");
		}
		printWriter.close();
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/renameBlock")
	public void rename(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		String newName = request.getParameter("newName");
		String blockName = request.getParameter("blockName");
		try {
			blockService.renameBlock(blockName, newName);
			printWriter.write("{\"res\":\"1\"}");
		} catch (BlockIsExistException e) {
			printWriter.write("{\"res\":\"0\"}");
		}
		printWriter.close();
	}
	
	/**
	 * 删除版块
	 * @throws IOException 
	 */
	@RequestMapping("/deleteBlock")
	public void deleteBlock(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		String blockName = request.getParameter("blockName");
		blockService.deleteBlock(blockName);
		printWriter.write("{\"res\":\"1\"}");
		printWriter.close();
	}

}	
