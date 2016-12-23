package com.xiaoyang.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiaoyang.dao.LoginDao;
import com.xiaoyang.entity.Admin;
import com.xiaoyang.entity.Sign;

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
	public boolean query(String _value,HttpSession session) {
		try {
			String[] dec = new String(new BASE64Decoder().decodeBuffer(_value)).split(":");
			Admin admin = new Admin();
			admin.setUsername(dec[0]);
			admin.setPassword(dec[1]);
			Admin _admin = this.loginDao.query(admin);
			if(_admin == null)
			{
				return false;
			}
			if(!admin.getUsername().equals(_admin.getUsername())&&!admin.getPassword().equals(_admin.getPassword()) )
			{
				return false;
			}
			session.setAttribute("user", _admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
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
}
