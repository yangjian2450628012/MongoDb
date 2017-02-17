package com.xiaoyang.entity.wdsq;

import java.io.Serializable;
import com.xiaoyang.util.system.DeAnnotaion;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * SQGL_ZXX 实体信息 
 * 申请管理信息
 * @author 应用引擎自动生成
 * @date 2017-02-14 21:23:36
 *	
 */

public class SQGL_ZXX implements Serializable{
	private static final long serialVersionUID = 1L;

	//主键id
	@DeAnnotaion(isPrimary=true)//自增
	private java.lang.String id;	
	//主信息外键
	private java.lang.String pid;
	//申请单名称
	private java.lang.String sqdname;
	//申请单id
	private java.lang.String sqdid;
	//申请内容
	private java.lang.String text;

	@DeAnnotaion(isNull=false) 
	public java.lang.String getId(){
		return id;
	}
	@DeAnnotaion(isNull=false) 
	public void setId(java.lang.String id){
		this.id = id;
	}	
	public java.lang.String getPid(){
		return pid;
	}	
	public void setPid(java.lang.String pid){
		this.pid = pid;
	}
	public java.lang.String getSqdname(){
		return sqdname;
	}	
	public void setSqdname(java.lang.String sqdname){
		this.sqdname = sqdname;
	}
	public java.lang.String getSqdid(){
		return sqdid;
	}	
	public void setSqdid(java.lang.String sqdid){
		this.sqdid = sqdid;
	}
	public java.lang.String getText(){
		return text;
	}	
	public void setText(java.lang.String text){
		this.text = text;
	}

}