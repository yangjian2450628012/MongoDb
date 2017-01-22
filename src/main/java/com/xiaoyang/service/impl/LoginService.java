package com.xiaoyang.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.xiaoyang.entity.Admin;
import com.xiaoyang.entity.Sign;

/** 
* @ClassName: LoginService 
*/
public interface LoginService {
	
	String query(String _value);
	
	boolean update(Admin admin);
	
	boolean delete(String username);
	
	List<Admin> queryAll();

	Sign querySign(String get_id);
	
	Admin queryAdminByid(String id);
}
