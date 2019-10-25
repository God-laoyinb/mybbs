package com.bbs.mapper;

import java.util.List;
import java.util.Map;

import com.bbs.exception.BlockIsExistException;
import com.bbs.pojo.Block;

/**
 * @author 
 * 版块Mapper
 */
public interface BlockMapper {

	/**
	 * 获取所有版块
	 * @param void
	 * @return 版块列表
	 */
	public List<Block> getAllBlock(); //获取所有版块(不包括被删除的版块)
	
	/**
	 * 通过版块编号获取版块对象
	 * @param blockId 版块编号
	 * @return 版块对象
	 */
	public Block getBlockById(Integer blockId);
	
	/**
	 * 通过版块名称获取版块对象
	 * @param blockName 版块名称
	 * @return 版块对象
	 */
	public Block getBlockByName(String blockName);
	
	/**
	 * 通过版块编号获取版块在数据库中的位置
	 * @param blockId 版块编号 
	 * @return 位置
	 */
	public Integer getBlockValueByBlockId(Integer blockId);

	/**
	 * 添加版块
	 * @param blockName 版块名称
	 * @throws BlockIsExistException 版块名称已存在异常
	 */
	public void addBlock(String blockName);
	
	/**
	 *更新版块信息 
	 *@param block 版块对象
	 */
	public void updateBlock(Block block);
	
}
