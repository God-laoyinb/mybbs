package com.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.bbs.pojo.Admin;
import com.bbs.service.AdminService;
import com.bbs.service.impl.AdminServiceImpl;

@Controller
@RequestMapping("/AdminManage")
public class AdminManage {
	
	@Resource(name="mv")
	private ModelAndView mv;
	
	@Resource(name="adminServiceImpl")
	private AdminService as;
	@Autowired
	private Admin admin ;
	
	/**
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/admin-list")
	public ModelAndView adminManage(HttpServletRequest request,HttpServletResponse response) {
		
		List<Admin> adminList = as.getAllAdmin();
		mv.addObject("adminList",adminList);
		mv.setViewName("admin/admin-list");
		return mv;
	}
	
	/**
	 * 添加管理员页面
	 * @param
	 * @return
	 */
	@RequestMapping("/admin-add")
	public ModelAndView addAdmin(HttpServletRequest request,HttpServletResponse response) {	
		
		mv.setViewName("admin/admin-add");
		return mv;
	}
	
	/**
	 * 更新管理员信息页面
	 * @param
	 * @return
	 */
	@RequestMapping("/admin-update")
	public ModelAndView admin_update(HttpServletRequest request,HttpServletResponse response) {
		//ModelAndView mv = new ModelAndView();
		mv.addObject("adminId",request.getParameter("id"));
//		if(null != request.getParameter("mobile")) {
//			mv.addObject("mobile",request.getParameter("mobile"));
//		} else {
//			mv.addObject("mobile","");
//		}
		if(null != request.getParameter("adminName")) {
			mv.addObject("adminName",request.getParameter("adminName"));
		} else {
			mv.addObject("adminName","");
		}
		if(null != request.getParameter("password")) {
			mv.addObject("password",request.getParameter("password"));
		} else {
			mv.addObject("password","");
		}
		mv.setViewName("admin/admin-update");
		return mv;
	}
	@RequestMapping("/user-update")
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/user-update");
		return mv;
	}
	/**
	  *  停用管理员
	 * @param
	 * @return
	 */
	@RequestMapping("/stopAdmin")
	public void stopAdmin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("com/cxf/pojo/applicationContext.xml");
		PrintWriter printWriter = response.getWriter();
		//AdminServiceImpl adminServiceImpl = (AdminServiceImpl)ctx.getBean("adminServiceImpl");
		String adminName = request.getParameter("adminName");
		//if(!adminServiceImpl.getRoleByName(adminName).equals("超级管理员")) {
			try {
				
				as.stopAdmin(adminName);
				
				printWriter.write("{\"res\":\"1\"}");
			} catch (Exception e) {
				printWriter.write("{\"res\":\"0\"}");
			}
			
		//} else {
			//printWriter.write("{\"res\":\"0\"}");
		//}
		printWriter.close();
	}
	@RequestMapping("/startAdmin")
	public void startAdmin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("com/cxf/pojo/applicationContext.xml");
		//AdminServiceImpl adminServiceImpl = (AdminServiceImpl)ctx.getBean("adminServiceImpl");
		PrintWriter printWriter = response.getWriter();
		String adminName = request.getParameter("adminName");
		as.startAdmin(adminName);
		printWriter.write("{\"res\":\"1\"}");
		printWriter.close();
	}
	/**
	  *   验证是否是本账号
	 * @param
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/validate") 
	public void validate(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		//先判断是否是本人或者是超级管理员
		//String nowAdmin = (String)request.getSession().getAttribute("adminName"); //当前登录的管理员
		String adminName = request.getParameter("adminName"); //要修改的管理员
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("com/cxf/pojo/applicationContext.xml");
		//AdminServiceImpl adminServiceImpl = (AdminServiceImpl)ctx.getBean("adminServiceImpl");
		Integer target = as.getAdminIdByName(adminName); //要操作的管理员信息的管理员编号
		Integer adminid = (Integer) request.getSession().getAttribute("adminId"); //当前登录的管理员ID
		if(target==adminid || adminid==1) { //如果是本人或者超级管理员
			printWriter.write("{\"res\":\"1\"}");
		} else {
			printWriter.write("{\"res\":\"0\"}");
		}
		printWriter.close();
		
	}
	/**
	  *  更新管理员信息
	 * @param
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/updateAdmin")
	public void updateAdmin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("com/cxf/pojo/applicationContext.xml");
		//AdminServiceImpl adminServiceImpl = (AdminServiceImpl)ctx.getBean("adminServiceImpl");
		//Admin admin = (Admin)ctx.getBean("admin");
		String adminName = request.getParameter("newName");
		PrintWriter printWriter = response.getWriter();
		 //改完要把session中当前登录管理员修改
		//修改前要判断是否重名
		if(null == as.findAdminByName(adminName)) { //不存在
			admin.setAdminName(request.getParameter("newName"));
			admin.setId(as.getAdminIdByName(request.getParameter("nowName")));
			System.out.println(request.getParameter("nowName"));
			
			
			admin.setPassword(request.getParameter("password"));
			as.updateAdmin(admin);
			printWriter.write("{\"res\":\"1\"}");
		} else {
			printWriter.write("{\"res\":\"2\"}"); //已存在(同名)
		}
		printWriter.close();
		//request.getSession().setAttribute("adminName",request.getParameter("adminName")); //修改session中当前管理员名
	}
	/**
	 * 添加管理员
	 * @param
	 * @return
	 */
	
	@RequestMapping("/addAdmin")
	public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		PrintWriter printWriter = response.getWriter();
//		AdminServiceImpl adminServiceImpl = (AdminServiceImpl)ctx.getBean("adminServiceImpl");
//		Integer adminId = Integer.parseInt((String)request.getSession().getAttribute("adminId"));
		// = (Admin)ctx.getBean("admin");
		
		
		String adminName=request.getParameter("adminName");
		//String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		admin.setAdminName(adminName);
		admin.setPassword(password);
//		if(1==adminId) { //是管理员
			try {
				if(null == as.findAdminAllByName(adminName)) { //该管理员不存在，则添加
					as.addAdmin(admin);
					printWriter.write("{\"res\":\"1\"}");
				} else {
					printWriter.write("{\"res\":\"0\"}");//管理员存在
				}
			} catch (Exception e) {
				throw new RuntimeException();
			}
//		} else {
//			printWriter.write("{\"res\":\"2\"}"); //不是超级管理员，无权限
//		}
		printWriter.close();
	}
}
