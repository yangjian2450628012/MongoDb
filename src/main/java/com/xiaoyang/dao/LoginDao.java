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

	/**
	 * 根据用户id查询用户信息
	 * @param id
	 * @return
	 */
	Admin queryAdminByid(String id);
}
