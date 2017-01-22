package com.xiaoyang.service.Impl.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xiaoyang.dao.system.AdminDao;
import com.xiaoyang.entity.system.Admin;
import com.xiaoyang.entity.system.AemEntity;
import com.xiaoyang.entity.system.JsonTreeData;
import com.xiaoyang.entity.system.Menu;
import com.xiaoyang.entity.system.Submenu;
import com.xiaoyang.entity.system.Trees;
import com.xiaoyang.util.system.ClassRowsMapper;
import com.xiaoyang.util.system.EasyuiResult;
import com.xiaoyang.util.system.TreeNodeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

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
		List<JsonTreeData> resultC = new ArrayList<JsonTreeData>();
		JsonTreeData JsonTreeDataZ = new JsonTreeData();;
		JsonTreeDataZ.setId("AAAAA");
		JsonTreeDataZ.setPid("0");
		JsonTreeDataZ.setText("业务模型分类");
		JsonTreeDataZ.setState("open");
		JsonTreeDataZ.setYlzd("fn");
		
		String sql = "select id,text,type from menu order by orderA asc";
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
		for (Map<String, Object> map : list) {
			JsonTreeData JsonTreeData = new JsonTreeData();
			JsonTreeData.setId(map.get("id").toString());
			JsonTreeData.setPid("AAAAA"); //根节点
			JsonTreeData.setText(map.get("text").toString()+"<span style='color: green;"
					+ "font-size: 12px;display: inline-block;text-decoration: none;"
					+ "vertical-align: top;white-space: nowrap;padding: 0 2px; height: 18px;line-height: 18px;'><主菜单></span>");
			JsonTreeData.setYlzd(map.get("type").toString());
			List<JsonTreeData> listC = this.queryMenuC(map.get("id"));
			if(listC != null && listC.size() > 0){
				JsonTreeData.setChildren(listC);
				JsonTreeData.setState("closed");
			}else{
				JsonTreeData.setState("open");
				
			}
			resultC.add(JsonTreeData);
		}
		if(resultC != null && resultC.size() > 0){
			JsonTreeDataZ.setChildren(resultC);
		}
		result.add(JsonTreeDataZ);
		return result;
	}
	
	/**
	 * 通过递归查询子节点
	 * @param id
	 * @return
	 */
	private List<JsonTreeData> queryMenuC(Object id){
		List<JsonTreeData> result = new ArrayList<JsonTreeData>();
		
		String sqlC = "select id,text,pid,url,type from submenu where pid ='"+id+"' order by orderA asc";
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sqlC);
		for (Map<String, Object> map : list) {
			JsonTreeData JsonTreeData = new JsonTreeData();
			JsonTreeData.setId(map.get("id").toString());
			if("fn".equals(map.get("type").toString())){
				JsonTreeData.setText(map.get("text").toString()+"<span style='color: green;"
						+ "font-size: 12px;display: inline-block;text-decoration: none;"
						+ "vertical-align: top;white-space: nowrap;padding: 0 2px; height: 18px;line-height: 18px;'><主菜单></span>");
			}else if("mn".equals(map.get("type").toString())){
				JsonTreeData.setText(map.get("text").toString()+"<span style='color: green;"
						+ "font-size: 12px;display: inline-block;text-decoration: none;"
						+ "vertical-align: top;white-space: nowrap;padding: 0 2px; height: 18px;line-height: 18px;'><功能菜单></span>");
			}else if("en".equals(map.get("type").toString())){
				JsonTreeData.setText(map.get("text").toString()+"<span style='color: green;"
						+ "font-size: 12px;display: inline-block;text-decoration: none;"
						+ "vertical-align: top;white-space: nowrap;padding: 0 2px; height: 18px;line-height: 18px;'><数据表></span>");
			}
			JsonTreeData.setPid(id.toString());
			JsonTreeData.setYlzd(map.get("type").toString());
			JsonTreeData.setUrl(map.get("url")==null ? "": map.get("url").toString());
			List<JsonTreeData> listC = this.queryMenuC(map.get("id")); //调用递归
			if(listC != null && listC.size() > 0){
				JsonTreeData.setChildren(listC);
				JsonTreeData.setState("closed");
			}else{
				JsonTreeData.setState("open");
			}
			result.add(JsonTreeData);
		}
		return result;
	}

	/* 
	 * 获取表结构信息
	 */
	public List<Map<String, Object>> queryEntityByid(String id) {
		List<Map<String,Object>> list = null;
		ResultSet rs = null;
		ResultSet Keys = null;
		ResultSetMetaData rm = null;
		Map<String,Object> map = null;
		Connection conn = null;
		try {
			//查询数据表
			ClassRowsMapper classmapper = new ClassRowsMapper<AemEntity>(AemEntity.class);
			List<AemEntity> list_aementity = this.jdbcTemplate.query("select id,package_id,name,comm,createtime,create_flag from aem_entity where package_id='"+id+"'", classmapper);
			if(list_aementity.size() > 0){
				AemEntity aementity = (AemEntity)list_aementity.get(0);
				list = new ArrayList<Map<String,Object>>();
				map = new HashMap<String, Object>();
				map.put("id", aementity.getId());
				map.put("comm", aementity.getComm());
				map.put("name", aementity.getName());
				map.put("package_id", aementity.getPackage_id());
				map.put("createtime", aementity.getCreatetime());
				map.put("create_flag", aementity.getCreate_flag());
				list.add(map);
				conn = this.jdbcTemplate.getDataSource().getConnection(); //得到连接
				rs = conn.getMetaData().getColumns(null, null, aementity.getName(), null);
				rm = rs.getMetaData();
				int columns = rm.getColumnCount();
				List<String> lista = new ArrayList<String>();
				
				for(int i=1;i<=columns;i++)
				{
					lista.add(rm.getColumnName(i));
				}
				
				//获取主键信息
				Set<String> keysSet =  new HashSet<String>(); 
				try {
					Connection connection = this.jdbcTemplate.getDataSource().getConnection();
					Keys =	conn.getMetaData().getPrimaryKeys(null, null, aementity.getName());
					while(Keys.next()){
						keysSet.add(Keys.getString("COLUMN_NAME"));
					}
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				while(rs.next())
				{
					 map = new HashMap<String, Object>();
				  for(int i=1;i<columns;i++)
				  {
					  map.put( lista.get(i), rs.getString(lista.get(i)));
				  }
				  //查看这一条属性是否包含主键
				 for (String string : keysSet) {
					 if(string.equals(map.get("COLUMN_NAME"))){
						 map.put("PRIMARYKEYS", "YES");
						 break;
					 }
				 }
				 if(!map.containsKey("PRIMARYKEYS"))map.put("PRIMARYKEYS", "NO");
				 list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(Keys != null) rs.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.xiaoyang.service.impl.AdminService#saveDataEntity(com.xiaoyang.entity.Submenu)
	 * 保存功能菜单实体数据
	 */
	public void editDataTable(Submenu submenu) throws Exception{
		StringBuilder sb = null;
		
		if("modify".equals(submenu.getType()))//修改修改功能菜单数据表名字
		{ 
			sb = new StringBuilder("update submenu set text ='");
			sb.append(submenu.getText()).append("' where id ='");
			sb.append(submenu.getId()).append("';");
		}else if("del".equals(submenu.getType())) //删除功能菜单表
		{
			//删除数据表
			List<Map<String, Object>> list_tablename = this.jdbcTemplate.queryForList("select name from aem_entity where package_id ='"+submenu.getId()+"'");
			if(list_tablename != null && list_tablename.size() > 0){
				this.jdbcTemplate.execute("delete from aem_entity where package_id ='"+submenu.getId()+"'");
				this.jdbcTemplate.execute("DROP TABLE IF EXISTS `"+list_tablename.get(0).get("name")+"`");
			}
			sb = new StringBuilder("delete from submenu where id='");
			sb.append(submenu.getId()).append("';");
		}else
		{
			SimpleDateFormat dataF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = dataF.format(new Date());
			sb = new StringBuilder("insert into submenu(id,text,pid,createtime,url,orderA,type) values('");
			sb.append(submenu.getId()).append("','");
			sb.append(submenu.getText()).append("','");
			sb.append(submenu.getPid()).append("','");
			sb.append(nowTime).append("','");
			sb.append(submenu.getUrl()).append("',");
			sb.append(1).append(",").append("'en' );");
		}
		this.jdbcTemplate.execute(sb.toString());
	}

	/* 构造创建表语句，并提交
	 * @see com.xiaoyang.service.impl.AdminService#createEntityTable(java.lang.String, java.lang.String)
	 */
	public void createEntityTable(String data, String tableInfo) throws Exception{
		JSONObject tableObject = JSONObject.fromObject(tableInfo); //表名信息
		//更新aem_entity表
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if("".equals(tableObject.getString("aem_entityId"))){ //新增
			if("".equals(tableObject.getString("entityName")))throw new Exception("实体名不能为空");
			if("".equals(tableObject.getString("remark")))throw new Exception("备注不能为空");
			UUID uuid = UUID.randomUUID();
			String id = uuid.toString().substring(0,6).toUpperCase();
			String sql = "insert into aem_entity(id,package_id,name,comm,createtime,create_flag) "
					+ "values('"+id+"','"+tableObject.getString("package_id")+"','"+tableObject.getString("entityName")+"',"
							+ "'"+tableObject.getString("remark")+"','"+sf.format(new Date())+"',1 )";
			this.jdbcTemplate.execute(sql);
		}else{
			String sql = "update aem_entity set name = '"+tableObject.getString("entityName")+"',comm ='"+tableObject.getString("remark")+"',"
					+ "createtime ='"+sf.format(new Date())+"',create_flag=1 where id = '"+tableObject.getString("aem_entityId")+"'";
			this.jdbcTemplate.update(sql);
		}
		JSONArray dataArray = JSONArray.fromObject(data); 
		if(dataArray == null || dataArray.size() == 0) return;
		//判断删除表
		this.jdbcTemplate.execute("DROP TABLE IF EXISTS `"+tableObject.getString("entityName")+"`");
		
		//遍历属性数组，组装建表语句
		StringBuilder sbr = new StringBuilder(); 
		Set<String> keys = new HashSet<String>();
		sbr.append("CREATE TABLE ").append("`"+tableObject.get("entityName")+"`").append("("); 
		for (int i = 0; i < dataArray.size(); i++) {
			JSONObject dataObject = JSONObject.fromObject(dataArray.get(i)); //转换为对象
			//处理特殊字符
			if(JSONNull.getInstance().equals(dataObject.get("COLUMN_NAME")))dataObject.put("COLUMN_NAME", null);
			if(JSONNull.getInstance().equals(dataObject.get("TYPE_NAME")))dataObject.put("TYPE_NAME", null);
			if(JSONNull.getInstance().equals(dataObject.get("COLUMN_SIZE")))dataObject.put("COLUMN_SIZE", null);
			if(JSONNull.getInstance().equals(dataObject.get("COLUMN_DEF")))dataObject.put("COLUMN_DEF", null);
			if(JSONNull.getInstance().equals(dataObject.get("REMARKS")))dataObject.put("REMARKS", null);
			//拼装sql	
			if("".equals(dataObject.get("COLUMN_NAME")) || dataObject.get("COLUMN_NAME") == null) throw new Exception("第"+(i+1)+"行属性名称不能为空"); 
			sbr.append("`"+dataObject.get("COLUMN_NAME")+"`");
			if("".equals(dataObject.get("TYPE_NAME")) || dataObject.get("TYPE_NAME") == null) throw new Exception("第"+(i+1)+"行数据类型不能为空");
			sbr.append(" "+dataObject.get("TYPE_NAME"));
			if(!"".equals(dataObject.get("COLUMN_SIZE")) && dataObject.get("COLUMN_SIZE") != null) sbr.append("("+dataObject.get("COLUMN_SIZE")+")");
			if("NO".equals(dataObject.get("IS_NULLABLE")))sbr.append(" NOT NULL"); 
			else if("YES".equals(dataObject.get("IS_NULLABLE")))sbr.append(" NULL");
			if("YES".equals(dataObject.get("IS_AUTOINCREMENT"))) sbr.append(" AUTO_INCREMENT");
			if(!"".equals(dataObject.get("COLUMN_DEF")) && dataObject.get("COLUMN_DEF") != null ) sbr.append(" DEFAULT '"+dataObject.get("COLUMN_DEF")+"'");
			if(!"".equals(dataObject.get("REMARKS")) && dataObject.get("REMARKS") != null) sbr.append(" COMMENT '"+dataObject.get("REMARKS")+"'");
			if("YES".equals(dataObject.get("PRIMARYKEYS")))keys.add(dataObject.get("COLUMN_NAME").toString());sbr.append(",");
		}
		if(keys.size() > 0){
			sbr.append("PRIMARY KEY (");
			for (String key : keys) {
				sbr.append("`"+key+"`,");
			}
			sbr.deleteCharAt(sbr.length()-1);//去掉多余逗号
			sbr.append(")");
		}else{
			sbr.deleteCharAt(sbr.length()-1);//去掉多余逗号
		}
		sbr.append(")").append("ENGINE=InnoDB DEFAULT CHARSET=utf8");
		System.out.println("建表语句====>"+sbr.toString()+"]");
		
		this.jdbcTemplate.execute(sbr.toString()); //创建表
	}
}
