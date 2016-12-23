package com.xiaoyang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiaoyang.dao.AdminDao;

/** 
* @ClassName: AttendanceServiceImpl 
* @Description: 签到签退实现类
* @author yang
* @date 2016年12月21日 下午2:51:52 
*  
*/
@Repository
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	private AdminDao admindao;
	
	public void saveSigninAndOut(String time,String type,String user_id) {
		this.admindao.saveSigninAndOut(time,type,user_id);
	}

}
