package com.xiaoyang.entity;

public class Zmenu {
	private String id;
	private String text;
	private String createtime;
	private int orderA;
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getOrderA() {
		return orderA;
	}
	public void setOrderA(int orderA) {
		this.orderA = orderA;
	}
}
