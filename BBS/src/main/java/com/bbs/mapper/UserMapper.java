package com.bbs.mapper;

import java.util.List;

import com.bbs.exception.UserExistException;
import com.bbs.pojo.User;

/**
 * @author 刘言
   * 用户Mapper
 */
public interface UserMapper {
	
	public User getUserById(Integer userId);
	public void insetUser(User user) throws UserExistException;
	public User getUserByName(String userName);
	public List<User> getAllUser();
	public void stopUser(String userName);
	public void startUser(String userName);
	public void updateUser(User user);
	public void deletUser(Integer uId);

}
