package com.bbs.service;

import java.util.List;
import java.util.Map;

import com.bbs.pojo.Paging;
import com.bbs.pojo.Post;

/**
 * @author 陈艺注
 * 帖子服务层
 */
public interface PostService {
	
	/**
	 * 通过用户id获取帖子
	 * @param map
	 * @return
	 */
	public Paging<Post> getMyPost(Map map);
	/**
	 * 以分页的形式获取帖子
	 * @param map 可选参数  blockName版块名称  keyword 关键字
	 * @return 帖子列表
	 */
	public Paging<Post> getPostByPaging(Map map);
	
	public Paging<Post> getPostBykeywordPaging(Map map);
	/**
	 * 通过帖子编号获取帖子对象
	 * @param postId 帖子编号
	 * @return 帖子对象
	 */
	public Post getPostById(Integer postId);
	
	/**
	 * 通过不同的类型和数量获取帖子列表
	 * @param type 获取的类型(1热门,2最新)
	 * @param num 获取的条数
	 * @return 帖子列表
	 */
	public List<Post> getPostByNumAndType(Integer num,Integer type);
	

	/**
	 * 以分页的形式获取全部帖子
	 * @param map page当前页，pageSize 页面大小
	 * @return 帖子列表
	 */
	public Paging<Post> getPostAllByPaging(Map map);
	
	/**
	 * 更新帖子
	 * @param map postId 帖子，cotent 内容，userName 用户名，isHot 热门，postTime 发帖时间，title 主题，blockName 版块   
	 * @return void
	 */
	public void updatePost(Map map);

	/**
	 * 删除帖子
	 * @param postId 帖子编号
	 */
	public void deletePost(Integer postId);

	/**
	 * 添加帖子
	 * @param post 帖子对象
	 */
	public void addPost(Post post);
	
	/**
	 * 
	 * @return
	 */
	public Paging<Post> getPost(Map map);
	
}
