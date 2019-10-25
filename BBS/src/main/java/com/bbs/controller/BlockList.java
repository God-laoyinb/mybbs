package com.bbs.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.pojo.Block;
import com.bbs.service.BlockService;
import com.bbs.service.impl.BlockServiceImpl;

/**
 * @author 
 * 版块页面控制器
 */
@Controller
@RequestMapping("/BlockList")
public class BlockList {

	@Resource(name="mv")
	private ModelAndView mv;
	@Resource(name="blockServiceImpl")
	private BlockService blockService;
	
	/**
	 * 版块列表页面
	 * @return
	 */
	@RequestMapping("/blockList")
	public ModelAndView blockList(HttpServletRequest request,HttpServletResponse response) {
		List<Block> blockList = blockService.getAllBlock();
		mv.addObject("blockList",blockList);
		mv.setViewName("blockList");
		return mv;
	}
	
}
