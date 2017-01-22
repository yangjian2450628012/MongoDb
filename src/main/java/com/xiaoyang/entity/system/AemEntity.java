package com.xiaoyang.entity.system;

/** 
* @ClassName: AemEntity 
* @Description: 存放实体
* @author YJ
* @date 2017年1月19日 下午11:07:50 
*  
*/
public class AemEntity {
	private String id;
	private String package_id;
	private String name;
	private String comm;
	private String createtime;
	private String create_flag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPackage_id() {
		return package_id;
	}
	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCreate_flag() {
		return create_flag;
	}
	public void setCreate_flag(String create_flag) {
		this.create_flag = create_flag;
	}
}
