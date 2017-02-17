package com.xiaoyang.dao.wdsq;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.xiaoyang.util.system.AeMustRollBackException;
import com.xiaoyang.entity.wdsq.SQGL_ZXX;
import com.xiaoyang.util.system.ClassRowsMapper;
import com.xiaoyang.util.system.ClassSQLWrite;
import com.xiaoyang.util.system.DeAnnotaion;
import com.xiaoyang.util.system.Tools;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * SQGL_ZXXDaoImpl
 * 申请管理信息数据访问实现类
 * @author 应用引擎自动生成
 * @date 2017-02-14 21:23:36
 *
 */ 
@Repository("sQGL_ZXXDao")
public class SQGL_ZXXDaoImpl implements SQGL_ZXXDao{
	private ClassSQLWrite classSQLWrite = new ClassSQLWrite();

	private JdbcTemplate jdbcTemplate;

	//插入记录
	public int insert(List<SQGL_ZXX> _sQGL_ZXXList) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		int count = 0;
		try {
			if(_sQGL_ZXXList == null || _sQGL_ZXXList.size() == 0)
				throw new RuntimeException("插入记录时输入的List不能为空，且长度不能为0！");
			//数据校验
			for (int i = 0; i < _sQGL_ZXXList.size(); i++) {
				Field field =  SQGL_ZXX.class.getDeclaredField("id");
				if(field.isAnnotationPresent(DeAnnotaion.class)){
					DeAnnotaion deannotaion = field.getAnnotation(DeAnnotaion.class);
					if(deannotaion.isPrimary() && Tools.StringIsNullOrSpace(_sQGL_ZXXList.get(i).getId()))
						throw new RuntimeException("插入记录时VO的ID主键不能为空！");
				}
			}
			List<String> sqlList = new ArrayList<String>();
			List<List> valueList = new ArrayList<List>();
			int sqlcount = this.classSQLWrite.batchClassSQLInsert(_sQGL_ZXXList,sqlList,valueList);
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
	public int delete(List<SQGL_ZXX> _sQGL_ZXXList) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		int count = 0;
		try {
			if(_sQGL_ZXXList == null || _sQGL_ZXXList.size() == 0)
				throw new RuntimeException("删除记录时输入的List不能为空，且长度不能为0！");
			//数据校验
			for (int i = 0; i < _sQGL_ZXXList.size(); i++) {
				Field field =  SQGL_ZXX.class.getDeclaredField("id");
				if(field.isAnnotationPresent(DeAnnotaion.class)){
					DeAnnotaion deannotaion = field.getAnnotation(DeAnnotaion.class);
					if(deannotaion.isPrimary() && Tools.StringIsNullOrSpace(_sQGL_ZXXList.get(i).getId()))
						throw new RuntimeException("删除记录时VO的ID主键不能为空！");
				}
			}
			List<String> sqlList = new ArrayList<String>();
			List<List> paramList = new ArrayList<List>();
			int sqlcount = this.classSQLWrite.batchClassSQLDelete(_sQGL_ZXXList,sqlList,paramList);
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
	public int update(List<SQGL_ZXX> _sQGL_ZXXList) 
			throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		int count = 0;
		List<String> sqlList = null;
		try {
			if(_sQGL_ZXXList == null || _sQGL_ZXXList.size() == 0)
				throw new RuntimeException("更新记录时输入的List不能为空，且长度不能为0！");
			//数据校验
			for (int i = 0; i < _sQGL_ZXXList.size(); i++) {
				Field field =  SQGL_ZXX.class.getDeclaredField("id");
				if(field.isAnnotationPresent(DeAnnotaion.class)){
					DeAnnotaion deannotaion = field.getAnnotation(DeAnnotaion.class);
					if(deannotaion.isPrimary() && Tools.StringIsNullOrSpace(_sQGL_ZXXList.get(i).getId()))
						throw new RuntimeException("更新记录时VO的ID主键不能为空！");
				}
			}
			sqlList = this.classSQLWrite.BatchClassSQLUpdate(_sQGL_ZXXList);
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
	public List<SQGL_ZXX> select(SQGL_ZXX _sQGL_ZXX) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		List<SQGL_ZXX> sQGL_ZXXList = null;
		String sql;

		sql = this.classSQLWrite.ClassSQLSelect(sQGL_ZXXList);
		ClassRowsMapper<SQGL_ZXX> classRowsMapper = new ClassRowsMapper<SQGL_ZXX>(SQGL_ZXX.class);

		sQGL_ZXXList = this.jdbcTemplate.query(sql, classRowsMapper);
		ClassSQLWrite.printSQL(sql);
		return sQGL_ZXXList;
	}

	//分页查询
	public List<SQGL_ZXX> selectByPage(SQGL_ZXX _SQGL_ZXX, Integer page, Integer rows) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List<SQGL_ZXX> SQGL_ZXXList = null;
		String sql; 
		
		sql = this.classSQLWrite.ClassSQLSelectByPage(SQGL_ZXXList);
		ClassRowsMapper<SQGL_ZXX> classRowsMapper = new ClassRowsMapper<SQGL_ZXX>(SQGL_ZXX.class);
		SQGL_ZXXList = this.jdbcTemplate.query(sql, classRowsMapper);
		ClassSQLWrite.printSQL(sql);
		
		return SQGL_ZXXList;
	}

	//查询记录行数
	@SuppressWarnings("deprecation")
	public Integer selectCount(SQGL_ZXX _sQGL_ZXX) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String sql;

		sql = this.classSQLWrite.ClassSQLSelectCount(_sQGL_ZXX);
		ClassSQLWrite.printSQL(sql);
		return this.jdbcTemplate.queryForInt(sql);
	}

	//自由条件查询
	public List<SQGL_ZXX> queryUserDefineCondition(String sql) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String sqlZ;
		List<SQGL_ZXX> sQGL_ZXXList;
		SQGL_ZXX sQGL_ZXX = new SQGL_ZXX();
		
		sqlZ = this.classSQLWrite.ClassSQLSelectUserDefineCondition(sQGL_ZXX,sql);
		ClassRowsMapper<SQGL_ZXX> classRowsMapper = new ClassRowsMapper<SQGL_ZXX>(SQGL_ZXX.class);
		sQGL_ZXXList = this.jdbcTemplate.query(sql,classRowsMapper);
		ClassSQLWrite.printSQL(sqlZ);
		
		return sQGL_ZXXList;
	}

	//自由条件查询分页
	public List<SQGL_ZXX> queryUserDefineConditionByPage(String sql, Integer page, Integer rows) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String sqlZ;
		List<SQGL_ZXX> sQGL_ZXXList;
		SQGL_ZXX sQGL_ZXX = new SQGL_ZXX();
		
		sqlZ = this.classSQLWrite.ClassSQLSelectUserDefineConditionByPage(sQGL_ZXX,sql,page,rows);
		ClassRowsMapper<SQGL_ZXX> classRowsMapper = new ClassRowsMapper<SQGL_ZXX>(SQGL_ZXX.class);
		sQGL_ZXXList = this.jdbcTemplate.query(sql,classRowsMapper);
		ClassSQLWrite.printSQL(sqlZ);
		
		return sQGL_ZXXList;
	}

	// 自定义SQL查询,需传入一整个SQL，但需注意规则：select 列名 as 实体属性名,...... from 表名。
	//表名可不是该实体，但结果集别名必须与实体属性名称一致。
	public List<SQGL_ZXX> queryForUserDefineSQL(String sql, SQGL_ZXX _sQGL_ZXX) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		//TODO
		return null;
	}

	//自定义条件查询 查询记录条数 实现函数
	public Integer queryForUserDefineSQLCount(String sql) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		if (Tools.StringIsNullOrSpace(sql)) {
			sql = "select count(1) from SQGL_ZXX ";
		} else {
			sql = "select count(1) from SQGL_ZXX " + sql;
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