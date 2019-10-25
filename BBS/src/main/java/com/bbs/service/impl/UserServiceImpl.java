package com.bbs.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.bbs.exception.UserExistException;
import com.bbs.mapper.UserMapper;
import com.bbs.pojo.User;
import com.bbs.service.UserService;
import com.bbs.util.MD5Util;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Resource(name="userMapper")
	private UserMapper userMapper;

	@Override
	public User getUserByName(String userName) {
		User user = userMapper.getUserByName(userName);
		return user;
	}

	@Override
	public Integer getUserIdByName(String userName) {
		User user = userMapper.getUserByName(userName);
		if(user==null) {
			return null;
		}
		else {
		return user.getuId();
		}
	}

	@Override
	public void addUser(User user) {
		
			String password = MD5Util.toMd5(user.getPassword());
			User user1 =new User();
			user1.setUserName(user.getUserName());
			user1.setPassword(password);
			user1.setState(user.getState());
			try {
				userMapper.insetUser(user1);
			} catch (UserExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public User findUserById(Integer userId) {
		return userMapper.getUserById(userId);
	}

	@Override
	public Integer getUserIdByUserName(String userName) {
		return null;
	}
	/**
	 * 获取所有用户
	 */
	@Override
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}

	@Override
	public void validate(String userName, String password) throws Exception {
		String password1=MD5Util.toMd5(password);
		
	}

	@Override
	public void deleteUser(User user) {
		userMapper.deletUser(user.getuId());
	}
	
	@Override
	public boolean validUser(String userName, String password) throws Exception {
		String password1=MD5Util.toMd5(password);
		User user = getUserByName(userName);
		if(password1.equals(user.getPassword())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void stopUser(String userName) {
		userMapper.stopUser(userName);
		
	}

	@Override
	public void startUser(String userName) {
		userMapper.startUser(userName);
		
	}

	/**
	 * 通过用户编号获取用户对象
	 * @param userId 用户编号
	 * @return 用户对象
	 */
	@Override
	public User getUserById(Integer userId) {
		return userMapper.getUserById(userId);
	}
	
}
