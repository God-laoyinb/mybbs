package com.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bbs.mapper.BlockMapper;
import com.bbs.mapper.PostMapper;
import com.bbs.mapper.UserMapper;
import com.bbs.pojo.Block;
import com.bbs.pojo.Paging;
import com.bbs.pojo.Post;
import com.bbs.service.PostService;

/**
 * @author 刘言
 * 帖子服务层实现类
 */
@Service("postServiceImpl")
public class PostServiceImpl implements PostService {

	@Resource(name="postMapper")
	private PostMapper postMapper;
	@Resource(name="paging")
	private Paging<Post> paging;
	@Resource(name="blockMapper")
	private BlockMapper blockMapper;
	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	/**
	 * 以分页的形式获取帖子
	 * @param map 必填参数 page 当前页   pageSize 页面大小
	 * @param map 可选参数  blockName版块名称  keyword 关键字
	 * @return 帖子列表
	 */
	public Paging<Post> getPostByPaging(Map map) {
		Integer currentPage = (Integer)map.get("page"); //当前页
		Integer pageSize = (Integer)map.get("pageSize");
		String blockName = (String)map.get("blockName");
		

		if(null != blockName) {
			Integer blockId = blockMapper.getBlockByName(blockName).getbId(); //版块编号 
			Map map1 = new HashMap<Object,Object>();
			map1.put("blockId",blockId);
			map1.put("start",(currentPage-1)*pageSize);
			map1.put("offset",pageSize);
			List<Post> postList = postMapper.getPostByBlockId(map1);
			paging.setList(postList); //test
			paging.setCurrentPage(currentPage);
			paging.setPageSize(pageSize);
			paging.setTotalCount(postMapper.getTotalCountByBlockId(blockId));
			//paging.setTotalCount(3); //test
		}
		
		
		return paging;
	}
	public Paging<Post> getPostBykeywordPaging(Map map) {
		Integer currentPage = (Integer)map.get("page"); //当前页
		Integer pageSize = (Integer)map.get("pageSize");
		String keyword = (String)map.get("keyword");
		
		if(null != keyword) {
			
			Map map1 = new HashMap<Object,Object>();
			map1.put("keyword",keyword);
			map1.put("start",(currentPage-1)*pageSize);
			map1.put("offset",pageSize);
			List<Post> postList = postMapper.getPostByKeyword(map1);
			paging.setList(postList); //test
			paging.setCurrentPage(currentPage);
			paging.setPageSize(pageSize);
			paging.setTotalCount(postMapper.getTotalCountByKeyword(map1));
			
		}
		return paging;
	}

	/**
	 * 通过帖子编号获取帖子对象
	 * @param postId 帖子编号
	 * @return 帖子对象
	 */
	public Post getPostById(Integer postId) {
		return postMapper.getPostById(postId);
	}

	/**
	 * 通过不同的类型和数量获取帖子列表
	 * @param type 获取的类型(1热门,2最新)
	 * @param num 获取的条数
	 * @return 帖子列表
	 */
	@Override
	public List<Post> getPostByNumAndType(Integer num, Integer type) {
		
		List<Post> postList = null;
		
		if (1==type) { //热门
 			postList = postMapper.getHotPostByNum(num);
		} 
		if (2==type) { //最新
			postList = postMapper.getLastPostByNum(num);
		}
		return postList;
	}

	/**
	 * 以分页的形式获取全部帖子
	 * @param map page当前页，pageSize 页面大小
	 * @return 帖子列表
	 */
	@Override
	public Paging<Post> getPostAllByPaging(Map map) {
		Integer currentPage = (Integer)map.get("page"); //当前页
		Integer pageSize = (Integer)map.get("pageSize");
		Map map1 = new HashMap<Object,Object>();
		map1.put("start",(currentPage-1)*pageSize);
		map1.put("offset",pageSize);
		List<Post> postList = postMapper.getAllPostByPaging(map1);
		paging.setList(postList); 
		paging.setCurrentPage(currentPage);
		paging.setPageSize(pageSize);
		paging.setTotalCount(postMapper.getTotalCount());
		
		return paging;
	}

	/**
	 * 更新帖子
	 * @param map cotent 内容，userName 用户名，isHot 热门，postTime 发帖时间，title 主题，blockName 版块   
	 * @return void
	 */
	@Override
	public void updatePost(Map map) {
		Integer postId = (Integer)map.get("postId");
		Post post = postMapper.getPostById(postId);
		String content = (String)map.get("content");
		Integer isHot = (Integer)map.get("isHot");
		String title = (String)map.get("title");
		String blockName = (String)map.get("blockName");
		post.setTitle(title);
		post.setContent(content);
		post.setIsHot(isHot);
		post.setBlock(blockMapper.getBlockByName(blockName));
		//字符串转时间
		postMapper.updatePost(post);
	}

	/**
	 * 删除帖子
	 * @param postId 帖子编号
	 */
	@Override
	public void deletePost(Integer postId) {
		postMapper.deletePost(postId);
	}

	/**
	 * 添加帖子
	 * @param post 帖子对象
	 */
	@Override
	public void addPost(Post post) {
		postMapper.addPost(post);
	}

	@Override
	public Paging<Post> getPost(Map map) {
		return null;
	}
	@Override
	public Paging<Post> getMyPost(Map map) {
		Integer currentPage = (Integer)map.get("page"); //当前页
		Integer pageSize = (Integer)map.get("pageSize");
		Integer userId = (Integer)map.get("userId");
		if(null != userId) {
			
			Map map1 = new HashMap<Object,Object>();
			map1.put("userId",userId);
			map1.put("start",(currentPage-1)*pageSize);
			map1.put("offset",pageSize);
			List<Post> postList = postMapper.getMyPost(map1);
			paging.setList(postList); //test
			paging.setCurrentPage(currentPage);
			paging.setPageSize(pageSize);
			paging.setTotalCount(postMapper.getTotalCountMypost(map1));
			
		}
		return paging;
	}
	
}
