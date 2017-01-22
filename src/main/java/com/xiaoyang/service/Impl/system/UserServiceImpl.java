package com.xiaoyang.service.Impl.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiaoyang.dao.system.IUserDao;
import com.xiaoyang.entity.system.User;

/** 
* @ClassName: UserServiceImpl 
*  
*/
@Repository
public class UserServiceImpl implements UserService {
	
	@Autowired
	private IUserDao iuserdao;
	
	public void update(User user) {
		this.iuserdao.update(user);
	}

	public void save(User user) {
		this.iuserdao.save(user);
	}

	public void insert(User user) {
		this.iuserdao.insert(user);
	}

	public User getByTel(String tel) {
		
		return this.iuserdao.getByTel(tel);
	}

	public void delete(User user) {
		this.iuserdao.delete(user);
	}

}
