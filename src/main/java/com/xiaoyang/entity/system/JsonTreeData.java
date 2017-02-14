package com.xiaoyang.entity.system;

import java.util.List;

/** 
* @ClassName: JsonTreeData 
* Easyui tree结构
*/
public class JsonTreeData {

	private String id;
	private String pid;
	private String text;
	private String state;
	private List<JsonTreeData> children;
	private String url;
	private String ylzd;
	private String packageN;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<JsonTreeData> getChildren() {
		return children;
	}
	public void setChildren(List<JsonTreeData> children) {
		this.children = children;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getYlzd() {
		return ylzd;
	}
	public void setYlzd(String ylzd) {
		this.ylzd = ylzd;
	}
	public String getPackageN() {
		return packageN;
	}
	public void setPackageN(String packageN) {
		this.packageN = packageN;
	}
}
