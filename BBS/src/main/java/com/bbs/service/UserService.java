package com.bbs.service;

import java.util.List;

import com.bbs.pojo.User;

/**
 * @author 刘言
 * 用户服务层
 */
public interface UserService {
	public User getUserByName(String userName);
	public Integer getUserIdByName(String userName);
	public void addUser(User user); //添加用户
	public void updateUser(User user); //更新用户信息
	public User findUserById(Integer userId); //通过用户ID查找用户
	public Integer getUserIdByUserName(String userName);
	public List<User> getAllUser();
	public void validate(String userName,String password) throws Exception; //验证身份
	public boolean validUser(String userName,String password) throws Exception; //验证身份
	public void deleteUser(User user);//删除用户
	public void stopUser(String userName);
	public void startUser(String userName);

	/**
	 * 通过用户编号获取用户对象
	 * @param userId 用户编号
	 * @return 用户对象
	 */
	public User getUserById(Integer userId);

}
