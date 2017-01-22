package com.xiaoyang.entity.system;

/** 
* @ClassName: Sign 
* @Description: 签到签退实体
* @author yang
* @date 2016年12月21日 下午3:00:17 
*  
*/
public class Sign {

	private String _id;
	private String user_id;
	private String signin;
	private String signout;
	private String desc;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSignin() {
		return signin;
	}
	public void setSignin(String signin) {
		this.signin = signin;
	}
	public String getSignout() {
		return signout;
	}
	public void setSignout(String signout) {
		this.signout = signout;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
