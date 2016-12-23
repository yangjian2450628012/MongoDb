package com.xiaoyang.dao;

import java.util.List;

import com.xiaoyang.entity.Admin;
import com.xiaoyang.entity.Menu;
import com.xiaoyang.entity.Trees;
import com.xiaoyang.util.EasyuiResult;

/** 
* @ClassName: EasyuitreeDao 
*/
public interface AdminDao {

	List<Trees> queryAll();
	
	EasyuiResult queryManagerAll(int page,int pageSize,Admin admin);
	
	List<Menu> queryById(String id);
	
	int updateAuth(String _id, String authmenu, String secondMenu);
	
	Admin editUse(String _id);

	/**
	 * @param list
	 * @return
	 */
	void insertAdmin(List<Admin> list);
	
	void saveSigninAndOut(String time,String type,String user_id);
}



