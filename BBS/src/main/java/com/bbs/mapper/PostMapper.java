package com.bbs.mapper;

import java.util.List;
import java.util.Map;

import com.bbs.pojo.Post;

/**
 * @author 陈艺注
 * 帖子Mapper
 */
public interface PostMapper {
	
	/**
	 * 通过版块编号获取帖子
	 * @param map blockId 帖子编号 start 起始位置 offset 偏移量
	 * @return 帖子列表
	 */
	public List<Post> getPostByBlockId(Map map);
	
	/**
	 * 通过关键词获取帖子
	 * @param map包括  keyword 关键词
	 * @return 帖子列表
	 */
	public List<Post> getPostByKeyword(Map map);
	/**
	 *根据用户id返回帖子
	 * @param map
	 * @return
	 */
	public List<Post> getMyPost(Map map);
	
	/**
	 * 根据条件获取分页总记录数
	 * @param map 
	 * @return 分页的总记录数
	 */
	public Integer getTotalCountByKeyword(Map map);

	/**
	 * 通过版块编号获取帖子总记录数
	 * @param map blockId 版块编号 start 起始位置  offset 偏移量
	 * @return 总记录数
	 */
	public Integer getTotalCountByBlockId(Integer blockId);
	
	/**
	 * 通过帖子编号获取帖子对象
	 * @param postId 帖子编号
	 * @return 帖子对象
	 */
	public Post getPostById(Integer postId);
	
	/**
	 * 获取倒数(以发帖时间算)num条的帖子
	 * @param num 要获取的条数
	 * @return 帖子列表
	 */
	public List<Post> getLastPostByNum(Integer num);
	
	/**
	 * 获取热门帖子
	 * @param num 获取条数
	 * @return 帖子列表
	 */
	public List<Post> getHotPostByNum(Integer num);

	/**
	 * 以分页的形式获取全部分类
	 * @param map start 起始位置  offset 偏移量 
	 * @return 分类列表
	 */
	public List<Post> getAllPostByPaging(Map map);
	
	/**
	 * 获取所有记录数
	 * @return 总记录数
	 */
	public Integer getTotalCount();
	
	/**
	 * 更新帖子对象
	 * @param post 帖子对象
	 * @return void
	 */
	public void updatePost(Post post);
	
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
	 * 根据不同条件获取帖子列表
	 * @param map 可选 start offset userId 用户编号 
	 * @return 帖子列表
	 */
	public List<Post> getPost(Map map);

	public Integer getTotalCountMypost(Map map);

}

