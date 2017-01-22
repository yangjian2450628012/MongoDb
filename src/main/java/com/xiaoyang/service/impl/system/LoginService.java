package com.xiaoyang.service.impl.system;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.xiaoyang.entity.system.Admin;
import com.xiaoyang.entity.system.Sign;

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
