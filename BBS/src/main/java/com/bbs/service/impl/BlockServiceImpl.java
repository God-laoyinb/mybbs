package com.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bbs.exception.BlockIsExistException;
import com.bbs.mapper.BlockMapper;
import com.bbs.mapper.PostMapper;
import com.bbs.pojo.Block;
import com.bbs.pojo.Post;
import com.bbs.service.BlockService;

/**
 * @author 
 * 版块服务层实现类
 */
@Service("blockServiceImpl")
public class BlockServiceImpl implements BlockService {

	@Resource(name="blockMapper")
	private BlockMapper blockMapper;
	@Resource(name="postMapper")
	private PostMapper postMapper;
	
	/**
	 * 获取所有版块
	 * @return 版块列表
	 */
	public List<Block> getAllBlock() {
		return blockMapper.getAllBlock();
	}

	/**
	 * 通过版块编号获取版块在数据库中的位置
	 * @param blockId 版块编号 
	 * @return 位置
	 */
	@Override
	public Integer getBlockValueByBlockId(Integer blockId) {
		return blockMapper.getBlockValueByBlockId(blockId);
	}

	/**
	 * 添加版块
	 * @param blockName 版块名称
	 * @throws BlockIsExistException 版块名称已存在异常
	 */
	@Override
	public void addBlock(String blockName) throws BlockIsExistException {
		//判断版块名称是否存在
		if (null !=blockMapper.getBlockByName(blockName)) { //已存在
			throw new BlockIsExistException();
		} else {
			blockMapper.addBlock(blockName);
		}
	}

	/**
	 * 版块重命名
	 * @param blockName 原来的名称
	 * @param newName 新名称
	 * @throws BlockIsExistException 名称已存在异常
	 */
	@Override
	public void renameBlock(String blockName, String newName) throws BlockIsExistException {
		Block block = blockMapper.getBlockByName(blockName); //原来的对象
		//判断版块名称是否存在,且不是自己
		
		
		if (null != blockMapper.getBlockByName(newName)) { //已存在
			throw new BlockIsExistException();
		} else {
			block.setBlockName(newName);
			block.setState(1);
			blockMapper.updateBlock(block);
		}
	}

	/**
	 * 删除版块
	 * @param blockName 版块名称
	 */
	@Override
	public void deleteBlock(String blockName) {
		Block block = blockMapper.getBlockByName(blockName);
		block.setState(0);
		//删除版块
		blockMapper.updateBlock(block);
		//把该版块下的帖子也删除
		Map map = new HashMap();
		map.put("blockId",block.getbId());
		map.put("start",0);
		map.put("offset",1000);
		List<Post> postList = postMapper.getPostByBlockId(map);
		for (Post p:postList) {
			postMapper.deletePost(p.getpId());
		}
	}

	/**
	 * 通过版块编号获取版块对象
	 * @param blockId 版块编号
	 * @return 版块对象
	 */
	@Override
	public Block getBlockById(Integer blockId) {
		return blockMapper.getBlockById(blockId);
	}

	/**
	 * 通过版块名称获取版块对象
	 * @param blockName 版块名称
	 * @return 版块对象
	 */
	@Override
	public Block getBlockByName(String blockName) {
		return blockMapper.getBlockByName(blockName);
	}
	
}
