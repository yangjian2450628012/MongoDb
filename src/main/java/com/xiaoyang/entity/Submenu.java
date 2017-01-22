package com.xiaoyang.entity;

/** 
* @ClassName: Submenu 
* @Description: 功能菜单实体 
* @author YJ
* @date 2017年1月20日 下午4:44:24 
*  
*/
public class Submenu extends Zmenu{
	private String pid;
	private String url;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
