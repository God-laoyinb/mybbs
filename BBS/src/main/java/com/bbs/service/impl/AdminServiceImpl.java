package com.bbs.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import com.bbs.mapper.AdminMapper;
import com.bbs.exception.AdminIsStopException;
import com.bbs.exception.AdminNonExistException;
import com.bbs.exception.PasswordErrorException;
import com.bbs.pojo.Admin;
import com.bbs.pojo.User;
import com.bbs.service.AdminService;
import com.bbs.util.MD5Util;

@Service("adminServiceImpl")
public class AdminServiceImpl implements AdminService {
	
	@Resource(name="adminMapper")
	private AdminMapper adminMapper;

	@Override
	public void stopAdmin(String adminName) {
		adminMapper.stopAdmin(adminName);
	}

	@Override
	public void startAdmin(String adminName) {
		adminMapper.startAdmin(adminName);
	}

	@Override
	public List<Admin> getAllAdmin() {
		
		List<Admin> list = adminMapper.getAllAdmin();
		return list;
	}

	@Override
	public String getRoleByName(String adminName) {
		return null;
	}

	@Override
	public Integer getAdminIdByName(String adminName) {
		Admin admin = adminMapper.getAdminByName(adminName);
		if(admin==null) {
			return null;
		}
		else {
			return admin.getId();
		}
	}

	@Override
	public void updateAdmin(Admin admin) {
		adminMapper.updateAdmin(admin);
	}

	@Override
	public void addAdmin(Admin admin) {
		adminMapper.insertAdmin(admin);
	}

	@Override
	public Admin findAdminByName(String name) {
		Admin admin = adminMapper.getAdminByName(name);
		if(admin!=null) {
			return admin;
		}
		else {			
		return null;
		}
	}
	@Override
	public Admin findAdminAllByName(String name) {
		Admin admin = adminMapper.getAdminAllByName(name);
		if(admin!=null) {
			return admin;
		}
		else {			
		return null;
		}
	}

	@Override
	public Admin getAdminById(Integer adminId) {
		return null;
	}

	/**
	 * 验证管理员身份
	 * @param adminName 管理员名
	 * @param password 密码
	 * @throws AdminNonExistException 管理员不存在异常
	 * @throws PasswordErrorException 密码错误异常
	 */
	@Override
	public void validate(String adminName, String password) throws AdminNonExistException,PasswordErrorException {
		//获取管理员对象
		Admin admin = adminMapper.getAdminByName(adminName);
		//判断管理员是否存在
		if(null == admin) { //管理员不存在
			throw new AdminNonExistException();
		}
		//判断密码是否正确
		if(!admin.getPassword().equals(MD5Util.toMd5(password))) {
			throw new PasswordErrorException();
		}
	}

	@Override
	public boolean validadmin(String adminName, String password) throws Exception {
		Admin admin = adminMapper.getAdminByName(adminName);
		String password1=MD5Util.toMd5(password);
		if(password1.equals(admin.getPassword())) {
			return true;
		}else {
			return false;
		}
		
	}
		
}
