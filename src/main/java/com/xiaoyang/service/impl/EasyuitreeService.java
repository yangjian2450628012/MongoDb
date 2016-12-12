package com.xiaoyang.service.impl;

import java.util.List;

import com.xiaoyang.entity.Admin;
import com.xiaoyang.entity.JsonTreeData;
import com.xiaoyang.entity.Menu;
import com.xiaoyang.util.EasyuiResult;

/** 
* @ClassName: EasyuitreeService 
*/
public interface EasyuitreeService {

	List<JsonTreeData> queryAll();
	
	EasyuiResult queryManagerAll(int page,int pageSize,Admin admin);
	
	List<Menu> queryById(String id);

	int updateAuth(String _id, String authmenu, String secondMenu);
	
	Admin editUse(String _id);
}
