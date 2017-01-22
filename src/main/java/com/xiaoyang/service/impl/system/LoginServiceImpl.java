package com.xiaoyang.service.impl.system;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiaoyang.dao.system.LoginDao;
import com.xiaoyang.entity.system.Admin;
import com.xiaoyang.entity.system.Sign;

import sun.misc.BASE64Decoder;

/** 
* @ClassName: LoginServiceImpl 
*/
@Repository
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	/**
	 * 根據查詢結果返回是真假
	 * @throws IOException 
	 */
	public String query(String _value) {
		String info = "";
		try {
			String[] dec = new String(new BASE64Decoder().decodeBuffer(_value)).split(":");
			Admin admin = new Admin();
			admin.setUsername(dec[0]);
			admin.setPassword(dec[1]);
			Admin _admin = this.loginDao.query(admin);
			if(_admin == null)
			{
				return info;
			}
			if(!admin.getUsername().equals(_admin.getUsername())&&!admin.getPassword().equals(_admin.getPassword()) )
			{
				return info;
			}
			//把用户信息加密放到cookie中
			info = _admin.get_id()+":"+admin.getUsername();
//			session.setAttribute("user", _admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	public boolean update(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Admin> queryAll() {
		return this.loginDao.queryAll();
	}

	public Sign querySign(String user_id) {
		return this.loginDao.querySign(user_id);
	}

	/* 根据id查询用户信息
	 * @see com.xiaoyang.service.impl.LoginService#queryAdminByid(java.lang.String)
	 */
	public Admin queryAdminByid(String id) {
		return this.loginDao.queryAdminByid(id);
	}
}
