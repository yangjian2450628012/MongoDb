package com.xiaoyang.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.xiaoyang.entity.Admin;

/** 
* @ClassName: LoginService 
*/
public interface LoginService {
	
	boolean query(String _value,HttpSession session);
	
	boolean update(Admin admin);
	
	boolean delete(String username);
	
	List<Admin> queryAll();
}
