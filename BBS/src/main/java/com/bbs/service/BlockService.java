package com.bbs.service;

import java.util.List;

import com.bbs.exception.BlockIsExistException;
import com.bbs.pojo.Block;

/**
 * @author 
 * 版块服务层
 */
public interface BlockService {

	/**
	 * 获取所有版块
	 * @return 版块列表
	 */
	public List<Block> getAllBlock();

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
	public void addBlock(String blockName) throws BlockIsExistException;
	
	/**
	 * 版块重命名
	 * @param blockName 原来的名称
	 * @param newName 新名称
	 * @throws BlockIsExistException 名称已存在异常
	 */
	public void renameBlock(String blockName,String newName) throws BlockIsExistException;
	
	/**
	 * 删除版块
	 * @param blockName 版块名称
	 */
	public void deleteBlock(String blockName);

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

}
