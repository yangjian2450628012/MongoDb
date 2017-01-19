package com.xiaoyang.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	/* *
	 * 查询树形菜单
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

	/* 
	 * 查询菜单拼装json
	 * 用原生jdbc查询数据库
	 */
	public List<JsonTreeData> queryMenuToList() {
		List<JsonTreeData> result = new ArrayList<JsonTreeData>();
		
		String sql = "select id,text from menu order by orderA asc";
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
		for (Map<String, Object> map : list) {
			JsonTreeData JsonTreeData = new JsonTreeData();
			JsonTreeData.setId(map.get("id").toString());
			JsonTreeData.setPid("0"); //根节点
			JsonTreeData.setText(map.get("text").toString());
			JsonTreeData.setState("open");
			List<JsonTreeData> listC = this.queryMenuC(map.get("id"));
			if(listC != null && listC.size() > 0){
				JsonTreeData.setChildren(listC);
			}
			result.add(JsonTreeData);
		}
		return result;
	}
	
	/**
	 * 通过递归查询子节点
	 * @param id
	 * @return
	 */
	private List<JsonTreeData> queryMenuC(Object id){
		List<JsonTreeData> result = new ArrayList<JsonTreeData>();
		
		String sqlC = "select id,text,pid,url from submenu where pid ='"+id+"' order by orderA asc";
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sqlC);
		
		for (Map<String, Object> map : list) {
			JsonTreeData JsonTreeData = new JsonTreeData();
			JsonTreeData.setId(map.get("id").toString());
			JsonTreeData.setText(map.get("text").toString());
			JsonTreeData.setPid(id.toString());
			JsonTreeData.setUrl(map.get("url")==null ? "": map.get("url").toString());
			List<JsonTreeData> listC = this.queryMenuC(map.get("id")); //调用递归
			JsonTreeData.setState("open");
			if(listC != null && listC.size() > 0){
				JsonTreeData.setChildren(listC);
			}
			result.add(JsonTreeData);
		}
		return result;
	}
	
}
