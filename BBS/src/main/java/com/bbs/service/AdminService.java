package com.bbs.service;

import java.util.List;
import com.bbs.pojo.Admin;

public interface AdminService {
	public void stopAdmin(String adminName);
	public void startAdmin(String adminName);
	public List<Admin> getAllAdmin();
	public String getRoleByName(String adminName);
	public Integer getAdminIdByName(String adminName);
	public void updateAdmin(Admin admin);
	public void addAdmin(Admin admin);
	public Admin findAdminByName(String name);
	public Admin getAdminById(Integer adminId);	
	/**
	 * 验证管理员身份 
	 * @param
	 * @return
	 */
	public void validate(String adminName,String password) throws Exception;
	public boolean validadmin(String adminName,String password) throws Exception;
	public Admin findAdminAllByName(String name) throws Exception;  
}
