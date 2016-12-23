package com.xiaoyang.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.xiaoyang.entity.Admin;
import com.xiaoyang.entity.Sign;

/** 
* @ClassName: LoginDaoImpl 
*/
@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
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
}
