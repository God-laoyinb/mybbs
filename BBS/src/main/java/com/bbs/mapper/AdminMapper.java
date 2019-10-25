
package com.bbs.mapper;

import java.util.List;

import com.bbs.pojo.Admin;

/**
 * @author 杨锐光
 * 管理员Mapper
 */
public interface AdminMapper {

	public void test();
	public List<Admin> getAdminById(Integer adminId);
	
	/**
	 * 通过管理员名称获取管理员对象
	 * @param adminName 管理员名称
	 * @return 管理员对象
	 */
	public Admin getAdminByName(String adminName);
	/**
	 * 通过管理员名称获取管理员对象(包括停用的)
	 * @param adminName
	 * @return
	 */
	public Admin getAdminAllByName(String adminName);
	/**
	 * 获取所有admin对象
	 * @return
	 */
	public List<Admin> getAllAdmin();
	public void insertAdmin(Admin admin);
	public void stopAdmin(String adminName);
	public void startAdmin(String adminName);
	public void updateAdmin(Admin admin);
	
}
