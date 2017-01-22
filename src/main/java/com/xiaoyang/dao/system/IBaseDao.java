package com.xiaoyang.dao.system;

/** 
* @ClassName: IBaseDao 
*/
public interface IBaseDao<User> {
	
	void update(User user);
	
	void save(User user);
	
	void insert(User user);
	
	User getByTel(String tel);
	
	void delete(User user);
}
