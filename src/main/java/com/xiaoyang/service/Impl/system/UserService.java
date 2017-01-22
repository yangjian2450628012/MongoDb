package com.xiaoyang.service.Impl.system;

import com.xiaoyang.entity.system.User;

/** 
* @ClassName: UserService 
*/
public interface UserService {
	
	void update(User user);
	
	void save(User user);
	
	void insert(User user);
	
	User getByTel(String tel);
	
	void delete(User user);
}
