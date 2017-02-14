package com.xiaoyang.service.Impl.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyang.dao.system.IUserDao;
import com.xiaoyang.entity.system.User;

/** 
* @ClassName: UserServiceImpl 
*  
*/
@Service("userService")
public class UserServiceImpl implements UserService {
	
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

	public IUserDao getIuserdao() {
		return iuserdao;
	}

	@Autowired
	public void setIuserdao(IUserDao iuserdao) {
		this.iuserdao = iuserdao;
	}
}
