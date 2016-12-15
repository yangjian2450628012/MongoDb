package com.xiaoyang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiaoyang.dao.AdminDao;
import com.xiaoyang.entity.Admin;
import com.xiaoyang.entity.JsonTreeData;
import com.xiaoyang.entity.Menu;
import com.xiaoyang.entity.Trees;
import com.xiaoyang.util.EasyuiResult;
import com.xiaoyang.util.TreeNodeUtil;

/** 
* @ClassName: EasyuitreeServiceImpl 
*/
@Repository
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao easyuitreeDao; 
	
	/* (non-Javadoc)
	 * @see com.xiaoyang.service.impl.EasyuitreeService#queryAll()
	 */
	public List<JsonTreeData> queryAll() {
		List<Trees> list = this.easyuitreeDao.queryAll();
		List<JsonTreeData> _list = new ArrayList<JsonTreeData>();
		for (Trees tree : list) {
			JsonTreeData treeData = new JsonTreeData();
			treeData.setId(tree.get_id());
			treeData.setPid(tree.getPid());
			treeData.setText(tree.getText());
			treeData.setUrl(tree.getUrl());
			_list.add(treeData);
		}
		return TreeNodeUtil.getfatherNode(_list);
	}

	/**
	 * 获取全部用户管理
	 */
	public EasyuiResult queryManagerAll(int page,int pageSize,Admin admin) {
		return this.easyuitreeDao.queryManagerAll(page, pageSize,admin);
	}

	/**
	 * 根据id查询用户菜单 
	 */
	public List<Menu> queryById(String id) {
		return this.easyuitreeDao.queryById(id);
	}

	public int updateAuth(String _id, String authmenu, String secondMenu) {
		return this.easyuitreeDao.updateAuth(_id, authmenu, secondMenu);
	}

	public Admin editUse(String _id) {
		return this.easyuitreeDao.editUse(_id);
	}

	/**
	 * 批量保存数据
	 */
	public void insertAdmin(List<Admin> list) {
		this.easyuitreeDao.insertAdmin(list);
	}
	
}
