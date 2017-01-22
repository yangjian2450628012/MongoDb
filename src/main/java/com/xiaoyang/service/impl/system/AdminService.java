package com.xiaoyang.service.impl.system;

import java.util.List;
import java.util.Map;

import com.xiaoyang.entity.system.Admin;
import com.xiaoyang.entity.system.JsonTreeData;
import com.xiaoyang.entity.system.Menu;
import com.xiaoyang.entity.system.Submenu;
import com.xiaoyang.util.EasyuiResult;

/** 
* @ClassName: EasyuitreeService 
*/
public interface AdminService {

	List<JsonTreeData> queryAll();
	
	EasyuiResult queryManagerAll(int page,int pageSize,Admin admin);
	
	List<Menu> queryById(String id);

	int updateAuth(String _id, String authmenu, String secondMenu);
	
	Admin editUse(String _id);
	
	void insertAdmin(List<Admin> list);
	
	List<JsonTreeData> queryMenuToList();

	List<Map<String,Object>> queryEntityByid(String id);

	/**
	 * @param 保存功能菜单数据表
	 * @return
	 */
	void editDataTable(Submenu submenu) throws Exception;

	/**
	 * 创建表
	 * @param data 数据表属性数组
	 * @param tableInfo 表信息
	 * @return 操作结果
	 */
	void createEntityTable(String data,String tableInfo) throws Exception;
}
