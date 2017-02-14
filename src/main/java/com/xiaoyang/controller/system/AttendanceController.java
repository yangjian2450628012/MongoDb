package com.xiaoyang.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyang.entity.system.Admin;
import com.xiaoyang.service.Impl.system.AttendanceService;

import net.sf.json.JSONObject;

/** 
* @ClassName: AttendanceController 
* @Description: 考勤操作
* @author yang
* @date 2016年12月21日 下午2:42:00 
*  
*/
@Controller
@RequestMapping("/sign")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceservice;
		
	@RequestMapping(value="/signinAndOut",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doSignIn(@RequestParam(value="date")Date date,@RequestParam(value="type")String type,HttpSession session){
		Map<String,Object> map = new HashMap<String, Object>();
		Admin admin = (Admin)session.getAttribute("user");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		this.attendanceservice.saveSigninAndOut(time,type,admin.get_id());
		map.put("success", true);
		if(type.equals("signin")){
			map.put("msg", "签到成功!");
		}else if(type.equals("signOut")){
			map.put("msg", "签退成功!");
		}
		return map;
	}
}
