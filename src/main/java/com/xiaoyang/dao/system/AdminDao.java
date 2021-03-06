package com.xiaoyang.dao.system;

import java.util.List;

import com.xiaoyang.entity.system.Admin;
import com.xiaoyang.entity.system.Menu;
import com.xiaoyang.entity.system.Trees;
import com.xiaoyang.util.system.EasyuiResult;

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



