package com.xiaoyang.entity;

import java.util.Date;

/** 
* @ClassName: User 
*/
public class User {
	
	private String telephone;
	private String email;
	private String nickname;
	private	Date lastLoginTime;
	private Date updateTime;
	private String gender;
	private String picurePath;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPicurePath() {
		return picurePath;
	}
	public void setPicurePath(String picurePath) {
		this.picurePath = picurePath;
	}
}
