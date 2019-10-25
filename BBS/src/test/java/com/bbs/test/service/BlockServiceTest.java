package com.bbs.test.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.exception.BlockIsExistException;
import com.bbs.service.BlockService;

public class BlockServiceTest {

	/**
	 * 测试添加版块
	 */
	@Ignore
	public void addBlock() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BlockService  blockService = ctx.getBean("blockServiceImpl",BlockService.class);
		try {
			blockService.addBlock("JAVA1");
		} catch (BlockIsExistException e) {
			e.printStackTrace();
		}
	}
	
}
