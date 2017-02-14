package com.xiaoyang.entity.system;

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
	private String packageN;
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
	public String getPackageN() {
		return packageN;
	}
	public void setPackageN(String packageN) {
		this.packageN = packageN;
	}
}
