package com.xiaoyang.service.impl;

/** 
* @ClassName: AttendanceService 
* @Description: 签到签退服务类
* @author yang
* @date 2016年12月21日 下午2:47:58 
*  
*/
public interface AttendanceService {
	void saveSigninAndOut(String time,String type,String user_id);
}
