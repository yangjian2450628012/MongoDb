package com.xiaoyang.entity.dblc;

import java.io.Serializable;
import com.xiaoyang.util.system.DeAnnotaion;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * DBLC 实体信息 
 * 待办流程测试生成实体
 * @author 应用引擎自动生成
 * @date 2017-02-06 15:22:08
 *	
 */

public class DBLC implements Serializable{
	private static final long serialVersionUID = 1L;

	//主键id
	@DeAnnotaion(isPrimary=true,is_autoincrement=true)//自增,主键
	private java.lang.Integer id;
	//主任务名称
	private java.lang.String text;
	//标记类型
	private java.lang.String type;
	//保存图片二进制
	private byte[] img;

	@DeAnnotaion(isNull=false) 
	public java.lang.Integer getId(){
		return id;
	}
	@DeAnnotaion(isNull=false) 
	public void setId(java.lang.Integer id){
		this.id = id;
	}	
	public java.lang.String getText(){
		return text;
	}	
	public void setText(java.lang.String text){
		this.text = text;
	}
	public java.lang.String getType(){
		return type;
	}	
	public void setType(java.lang.String type){
		this.type = type;
	}
	public byte[] getImg(){
		return img;
	}	
	public void setImg(byte[] img){
		this.img = img;
	}

}