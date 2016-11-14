package com.xiaoyang.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.xiaoyang.entity.Admin;
import com.xiaoyang.entity.Menu;
import com.xiaoyang.entity.Trees;
import com.xiaoyang.util.EasyuiResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
* @ClassName: EasyuitreeDaoImpl 
*/
@Repository
public class EasyuitreeDaoImpl implements EasyuitreeDao{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * 获取全部
	 * @return
	 */
	public List<Trees> queryAll(){
		return this.mongoTemplate.findAll(Trees.class);
	}

	/**
	 * Mongodb分页查询,模糊查询
	 */
	public EasyuiResult queryManagerAll(int page, int pageSize,Admin admin) {
		//查询指定列
		/*DBObject fieldObject = new BasicDBObject();
		fieldObject.put("password", 0);*/
		
		Query query = new Query();
		
		if(!"".equals(admin.getUsername()) && admin.getUsername() !=null){//完全模糊查询
			Pattern pattern= Pattern.compile("^.*"+admin.getUsername()+".*$", Pattern.CASE_INSENSITIVE);
			query.addCriteria(Criteria.where("username").regex(pattern));
		}
		if(!"".equals(admin.getDept()) && admin.getDept()!=null){//精准查询
			Pattern pattern= Pattern.compile("^"+admin.getDept()+"$", Pattern.CASE_INSENSITIVE);
			query.addCriteria(Criteria.where("dept").regex(pattern));
		}
		if(!"".equals(admin.getJob()) && admin.getJob()!=null){//完全模糊查询
			Pattern pattern= Pattern.compile("^.*"+admin.getJob()+".*$", Pattern.CASE_INSENSITIVE);
			query.addCriteria(Criteria.where("job").regex(pattern));
		}
		if(!"".equals(admin.getName()) && admin.getName()!=null){//完全模糊查询
			Pattern pattern= Pattern.compile("^.*"+admin.getName()+".*$", Pattern.CASE_INSENSITIVE);
			query.addCriteria(Criteria.where("name").regex(pattern));
		}
		query.skip((page-1)*pageSize); //从第几条开始显示
		query.limit(pageSize);//一页显示多少条数据
		Sort sort = new Sort("createtime");
		query.with(sort);
		
		return new EasyuiResult(this.mongoTemplate.count(query, Admin.class), this.mongoTemplate.find(query, Admin.class));
	}

	public List<Menu> queryById(String id) {
		//查询_id = 1的三个字段
		DBObject dbObject = new BasicDBObject();
		dbObject.put("_id", id);
		DBObject fieldObject = new BasicDBObject();
		fieldObject.put("authmenu", 1);
		fieldObject.put("secondMenu", 1);
		fieldObject.put("_id", 1);
		fieldObject.put("username", 1);
		Query query = new BasicQuery(dbObject, fieldObject);
		
		Admin admin = this.mongoTemplate.findOne(query, Admin.class);
		Menu menu = new Menu();
		menu.set_id(admin.get_id());
		menu.setAuthmenu(admin.getAuthmenu());
		menu.setSecondMenu(admin.getSecondMenu());
		menu.setUsername(admin.getUsername());
		
		List<Menu> _list = new ArrayList<Menu>();
		_list.add(menu);
		
		menu = null;
		//查询全部菜单
		menu = this.mongoTemplate.findOne(new Query().addCriteria(Criteria.where("_id").is("1221111")), Menu.class);
		_list.add(menu);
		
		menu = null;
		
		return _list;
	}

	 /**
	  *保存更新用户权限 
	  **/
	public int updateAuth(String _id, String authmenu, String secondMenu) {
		
		Update update = new Update();
		update.set("authmenu", JSONArray.fromObject(authmenu));
		update.set("secondMenu", JSONArray.fromObject(secondMenu));
		update.set("name", "人事部小杨");
		return mongoTemplate.upsert(new Query(Criteria.where("_id").is(_id)), update, Admin.class).getN();
	}
}
