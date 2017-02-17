package com.xiaoyang.service.Impl.system;

import java.io.IOException;
import java.util.List;

import com.xiaoyang.entity.system.Admin;
import com.xiaoyang.entity.system.Sign;

/** 
* @ClassName: LoginService 
*/
public interface LoginService {
	
	String query(String _value) throws IOException;
	
	boolean update(Admin admin);
	
	boolean delete(String username);
	
	List<Admin> queryAll();

	Sign querySign(String get_id);
	
	Admin queryAdminByid(String id);
}
