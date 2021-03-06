package com.xiaoyang.dao.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.xiaoyang.entity.system.Admin;
import com.xiaoyang.entity.system.Sign;

/** 
* @ClassName: LoginDaoImpl 
*/
@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

	private MongoTemplate mongoTemplate;

	/**
	 * 查詢用戶
	 */
	public Admin query(Admin admin) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(admin.getUsername()));
		query.addCriteria(Criteria.where("password").is(admin.getPassword()));
		return this.mongoTemplate.findOne(query, Admin.class, "admin");
	}

	public boolean update(Admin admin) {
		return false;
	}

	public boolean delete(String username) {
		return false;
	}

	/**
	 * 查詢全部
	 */
	public List<Admin> queryAll() {
		return this.mongoTemplate.findAll(Admin.class);
	}

	/**
	 * 查询考勤记录
	 */
	public Sign querySign(String user_id) {
		return this.mongoTemplate.findOne(new Query(Criteria.where("user_id").is(user_id)), Sign.class);
	}

	/* 根据用户id查询用户信息
	 * @see com.xiaoyang.dao.LoginDao#queryAdminByid(java.lang.String)
	 */
	public Admin queryAdminByid(String id) {
		return this.mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), Admin.class);
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
}
