package com.xiaoyang.dao.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
import com.xiaoyang.entity.system.User;

/** 
* @ClassName: IUserDao 
*/
@Repository
public class IUserDao implements IBaseDao<User> {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void update(User user) {
		Query query = new Query();
		query.addCriteria(new Criteria("telephone").is(user.getTelephone()));
		Update update = new Update();
		update.set("email", user.getEmail());
		update.set("telephone", user.getTelephone());
		update.set("gender", user.getGender());
		update.set("lastLoginTime", user.getLastLoginTime());
		update.set("nickname", user.getNickname());
		update.set("picurePath", user.getPicurePath());
		
		this.mongoTemplate.updateFirst(query, update, User.class);
	}

	/**
	 * save函数参数条件，调用了insert或update函数:有则改之，无则加之
	 */
	public void save(User user) {
		this.mongoTemplate.save(user);
	}

	/**
	 * insert的对象如果存在则不会修改之前的值，也不会重新增加
	 */
	public void insert(User user) {
		//save函数根据参数条件，调用了insert或update函数:有则改之,无则加之
		this.mongoTemplate.insert(user);
	}

	/**
	 * 根据手机获取单个
	 */
	public User getByTel(String tel) {
		Query query = new Query();
		query.addCriteria(new Criteria("telephone").is(tel));
		return this.mongoTemplate.findOne(query, User.class);
	}

	/**
	 * 删除
	 */
	public void delete(User user) {
		WriteResult result = this.mongoTemplate.remove(user);
		System.out.println(result.getCachedLastError());
	}
}
