package com.xiaoyang.service.Impl.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyang.dao.system.AdminDao;

/** 
* @ClassName: AttendanceServiceImpl 
* @Description: 签到签退实现类
* @author yang
* @date 2016年12月21日 下午2:51:52 
*  
*/
@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {
	
	private AdminDao admindao;
	
	public void saveSigninAndOut(String time,String type,String user_id) {
		this.admindao.saveSigninAndOut(time,type,user_id);
	}

	public AdminDao getAdmindao() {
		return admindao;
	}
	@Autowired
	public void setAdmindao(AdminDao admindao) {
		this.admindao = admindao;
	}
	
	
}
