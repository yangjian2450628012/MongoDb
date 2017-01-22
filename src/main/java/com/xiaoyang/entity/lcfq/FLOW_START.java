package com.xiaoyang.entity.lcfq;

import org.springframework.data.annotation.Id;

import com.xiaoyang.util.system.DeAnnotaion;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * FLOW_START 实体信息 
 * 个人-流程发起
 * @author 应用引擎自动生成
 * @date 2017-1-22 09:45:34
 *	
 */

public class FLOW_START {
	@DeAnnotaion(is_autoincrement=false)//表示自增
	private Integer id; 
	private String name;
	private Integer age;
	private Integer class_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getClass_id() {
		return class_id;
	}
	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}
}
