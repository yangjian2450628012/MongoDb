package com.xiaoyang.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiaoyang.dao.LoginDao;
import com.xiaoyang.entity.Admin;

import sun.misc.BASE64Encoder;

/** 
* @ClassName: LoginServiceImpl 
*/
@Repository
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	/**
	 * 根據查詢結果返回是真假
	 */
	public boolean query(String _value,HttpSession session) {
		try {
			String[] dec = new BASE64Encoder().encode(_value.getBytes("utf-8")).split(":");
			//String dec[] = new String (Base64.decodeBase64(_value.getBytes("utf-8"))).split(":");
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
		} catch (UnsupportedEncodingException e) {
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
}
