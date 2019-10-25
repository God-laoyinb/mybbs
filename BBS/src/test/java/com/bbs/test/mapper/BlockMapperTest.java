package com.bbs.test.mapper;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.mapper.BlockMapper;
import com.bbs.pojo.Block;

/**
 * @author 
 * 版块Mapper测试类
 */
public class BlockMapperTest {

	/**
	 * 测试获取所有版块
	 */
	@Ignore
	public void getAllBlock() { // 通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BlockMapper blockMapper = ctx.getBean("blockMapper",BlockMapper.class);
		List<Block> blockList = blockMapper.getAllBlock();
		for (Block b:blockList) {
			System.out.println(b.getBlockName());
		}
	}

	/**
	 * 测试通过版块编号获取版块对象 
	 */
	@Ignore
	public void getBlockById() { //通过
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BlockMapper blockMapper = ctx.getBean("blockMapper",BlockMapper.class);
		Block block = blockMapper.getBlockById(1);
		System.out.println(block.getBlockName());
	}

	/**
	 * 测试通过版块编号获取版块在数据库中的位置
	 * @param 
	 * @return 
	 */
	@Test
	public void getBlockValueByBlockId() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BlockMapper blockMapper = ctx.getBean("blockMapper",BlockMapper.class);
		Integer i = blockMapper.getBlockValueByBlockId(2);
		System.out.println(i);
	}
	
}
