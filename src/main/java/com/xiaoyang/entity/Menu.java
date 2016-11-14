package com.xiaoyang.entity;

/** 
* @ClassName: Menu 
* @Description: 菜单实体
* @author yang
* @date 2016年11月7日 下午9:27:24 
*  
*/
public class Menu {

	private String _id;
	private String authmenu;
	private String secondMenu;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getAuthmenu() {
		return authmenu;
	}
	public void setAuthmenu(String authmenu) {
		this.authmenu = authmenu;
	}
	public String getSecondMenu() {
		return secondMenu;
	}
	public void setSecondMenu(String secondMenu) {
		this.secondMenu = secondMenu;
	}
}
