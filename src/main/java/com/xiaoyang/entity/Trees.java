package com.xiaoyang.entity;

/** 
* @ClassName: tree 
*/
public class Trees {

	private String _id;
	private String pid;
	private String text;
	private String createtime;
	private String userinfo;
	private String url;
	private String orgid;
	private String oorid;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOorid() {
		return oorid;
	}
	public void setOorid(String oorid) {
		this.oorid = oorid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
