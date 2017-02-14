package com.xiaoyang.dao.dblc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xiaoyang.entity.dblc.DBLC;
import com.xiaoyang.util.system.AeMustRollBackException;
import com.xiaoyang.util.system.ClassRowsMapper;
import com.xiaoyang.util.system.ClassSQLWrite;
import com.xiaoyang.util.system.DeAnnotaion;
import com.xiaoyang.util.system.Tools;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * DBLCDaoImpl
 * 待办流程测试生成实体数据访问实现类
 * @author 应用引擎自动生成
 * @date 2017-02-06 15:22:09
 *
 */ 
@Repository("dBLCDao")
public class DBLCDaoImpl implements DBLCDao{
	private ClassSQLWrite classSQLWrite = new ClassSQLWrite();

	private JdbcTemplate jdbcTemplate;

	//插入记录
	public int insert(List<DBLC> _dBLCList) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		int count = 0;
		try {
			if(_dBLCList == null || _dBLCList.size() == 0)
				throw new RuntimeException("插入记录时输入的List不能为空，且长度不能为0！");
			//数据校验
			for (int i = 0; i < _dBLCList.size(); i++) {
				Field field =  DBLC.class.getDeclaredField("id");
				if(field.isAnnotationPresent(DeAnnotaion.class)){
					DeAnnotaion deannotaion = field.getAnnotation(DeAnnotaion.class);
					if(deannotaion.is_autoincrement())continue; 
					if(deannotaion.isPrimary() && (_dBLCList.get(i).getId() ==null || _dBLCList.get(i).getId() == 0)) 
						throw new RuntimeException("插入记录时VO的ID主键不能为空！"); 
				}
			}
			List<String> sqlList = new ArrayList<String>();
			List<List> valueList = new ArrayList<List>();
			int sqlcount = this.classSQLWrite.batchClassSQLInsert(_dBLCList,sqlList,valueList);
			if(sqlList == null || sqlList.size() ==0) 
				throw new RuntimeException("生成的插入SQL为空，请检查");
			ClassSQLWrite.printSQL("成功生成 "+sqlcount+" 条，插入SQL语句。");
			for (int i = 0; i < sqlList.size(); i++) {
				count ++;
				this.getJdbcTemplate().update(sqlList.get(i), valueList.get(i).toArray());
			}
			return count;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	//删除记录
	public int delete(List<DBLC> _dBLCList) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		int count = 0;
		try {
			if(_dBLCList == null || _dBLCList.size() == 0)
				throw new RuntimeException("删除记录时输入的List不能为空，且长度不能为0！");
			//数据校验
			for (int i = 0; i < _dBLCList.size(); i++) {
				Field field =  DBLC.class.getDeclaredField("id");
				if(field.isAnnotationPresent(DeAnnotaion.class)){
					DeAnnotaion deannotaion = field.getAnnotation(DeAnnotaion.class);
					if(deannotaion.isPrimary() && (_dBLCList.get(i).getId() == null || _dBLCList.get(i).getId() == 0)) 
						throw new RuntimeException("删除记录时VO的ID主键不能为空！"); 
				}
			}
			List<String> sqlList = new ArrayList<String>();
			List<List> paramList = new ArrayList<List>();
			int sqlcount = this.classSQLWrite.batchClassSQLDelete(_dBLCList,sqlList,paramList);
			if(sqlList == null || sqlList.size() ==0) 
				throw new RuntimeException("删除的插入SQL为空，请检查");
			ClassSQLWrite.printSQL("成功生成 "+sqlcount+" 条，更新SQL语句。");
			for (int i = 0; i < sqlList.size(); i++) {
				count ++;
				this.jdbcTemplate.update(sqlList.get(i), paramList.get(i).toArray());
			}
			return count;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	//更新记录
	public int update(List<DBLC> _dBLCList) 
			throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		int count = 0;
		List<String> sqlList = null;
		try {
			if(_dBLCList == null || _dBLCList.size() == 0)
				throw new RuntimeException("更新记录时输入的List不能为空，且长度不能为0！");
			//数据校验
			for (int i = 0; i < _dBLCList.size(); i++) {
				Field field =  DBLC.class.getDeclaredField("id");
				if(field.isAnnotationPresent(DeAnnotaion.class)){
					DeAnnotaion deannotaion = field.getAnnotation(DeAnnotaion.class);
					if(deannotaion.isPrimary() && (_dBLCList.get(i).getId() == null || _dBLCList.get(i).getId() == 0)) 
						throw new RuntimeException("更新记录时VO的ID主键不能为空！"); 
				}
			}
			sqlList = this.classSQLWrite.BatchClassSQLUpdate(_dBLCList);
			if(sqlList == null || sqlList.size() ==0) 
				throw new RuntimeException("更新的插入SQL为空，请检查");
			ClassSQLWrite.printSQL("成功生成 "+sqlList.size()+" 条，更新SQL语句。");
			for (String sql : sqlList) {
				count ++;
				classSQLWrite.printSQL(sql); //输出SQL日志
				this.jdbcTemplate.execute(sql); //执行sql
			}
			return count;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	//使用对象检索返回List
	public List<DBLC> select(DBLC _dBLC) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		List<DBLC> dBLCList = null;
		String sql;

		sql = this.classSQLWrite.ClassSQLSelect(dBLCList);
		ClassRowsMapper<DBLC> classRowsMapper = new ClassRowsMapper<DBLC>(DBLC.class);

		dBLCList = this.jdbcTemplate.query(sql, classRowsMapper);
		ClassSQLWrite.printSQL(sql);
		return dBLCList;
	}

	//分页查询
	public List<DBLC> selectByPage(DBLC _DBLC, Integer page, Integer rows) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List<DBLC> DBLCList = null;
		String sql; 
		
		sql = this.classSQLWrite.ClassSQLSelectByPage(DBLCList);
		ClassRowsMapper<DBLC> classRowsMapper = new ClassRowsMapper<DBLC>(DBLC.class);
		DBLCList = this.jdbcTemplate.query(sql, classRowsMapper);
		ClassSQLWrite.printSQL(sql);
		
		return DBLCList;
	}

	//查询记录行数
	@SuppressWarnings("deprecation")
	public Integer selectCount(DBLC _dBLC) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String sql;

		sql = this.classSQLWrite.ClassSQLSelectCount(_dBLC);
		ClassSQLWrite.printSQL(sql);
		return this.jdbcTemplate.queryForInt(sql);
	}

	//自由条件查询
	public List<DBLC> queryUserDefineCondition(String sql) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String sqlZ;
		List<DBLC> dBLCList;
		DBLC dBLC = new DBLC();
		
		sqlZ = this.classSQLWrite.ClassSQLSelectUserDefineCondition(dBLC,sql);
		ClassRowsMapper<DBLC> classRowsMapper = new ClassRowsMapper<DBLC>(DBLC.class);
		dBLCList = this.jdbcTemplate.query(sql,classRowsMapper);
		ClassSQLWrite.printSQL(sqlZ);
		
		return dBLCList;
	}

	//自由条件查询分页
	public List<DBLC> queryUserDefineConditionByPage(String sql, Integer page, Integer rows) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String sqlZ;
		List<DBLC> dBLCList;
		DBLC dBLC = new DBLC();
		
		sqlZ = this.classSQLWrite.ClassSQLSelectUserDefineConditionByPage(dBLC,sql,page,rows);
		ClassRowsMapper<DBLC> classRowsMapper = new ClassRowsMapper<DBLC>(DBLC.class);
		dBLCList = this.jdbcTemplate.query(sql,classRowsMapper);
		ClassSQLWrite.printSQL(sqlZ);
		
		return dBLCList;
	}

	// 自定义SQL查询,需传入一整个SQL，但需注意规则：select 列名 as 实体属性名,...... from 表名。
	//表名可不是该实体，但结果集别名必须与实体属性名称一致。
	public List<DBLC> queryForUserDefineSQL(String sql, DBLC _dBLC) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		//TODO
		return null;
	}

	//自定义条件查询 查询记录条数 实现函数
	public Integer queryForUserDefineSQLCount(String sql) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		if (Tools.StringIsNullOrSpace(sql)) {
			sql = "select count(1) from DBLC ";
		} else {
			sql = "select count(1) from DBLC " + sql;
		}
		
		ClassSQLWrite.printSQL(sql);
		return jdbcTemplate.queryForInt(sql);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}	