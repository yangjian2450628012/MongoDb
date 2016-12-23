package com.xiaoyang.dao;

import java.util.List;

import com.xiaoyang.entity.Admin;
import com.xiaoyang.entity.Sign;

/** 
* @ClassName: LoginDao 
*/
public interface LoginDao {
	
	Admin query(Admin admin);
	
	boolean update(Admin admin);
	
	boolean delete(String username);
	
	List<Admin> queryAll();
	
	Sign querySign(String user_id);
}
