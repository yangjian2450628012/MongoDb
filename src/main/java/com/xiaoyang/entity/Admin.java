package com.xiaoyang.entity;

/** 
* @ClassName: Admin 
*/
public class Admin {

	private String _id;
	private String username;
	private String password;
	private String createtime;
	private String dept;
	private String organization;
	private String job;
	private String authmenu;
	private String name;
	private String secondMenu;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getSecondMenu() {
		return secondMenu;
	}
	public void setSecondMenu(String secondMenu) {
		this.secondMenu = secondMenu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getAuthmenu() {
		return authmenu;
	}
	public void setAuthmenu(String authmenu) {
		this.authmenu = authmenu;
	}
	
	
}
