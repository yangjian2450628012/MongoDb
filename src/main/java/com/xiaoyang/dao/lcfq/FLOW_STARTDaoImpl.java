package com.xiaoyang.dao.lcfq;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xiaoyang.entity.lcfq.FLOW_START;
import com.xiaoyang.util.system.ClassRowsMapper;
import com.xiaoyang.util.system.ClassSQLWire;
import com.xiaoyang.util.system.DeAnnotaion;
import com.xiaoyang.util.system.Tools;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * FLOW_STARTDaoImpl
 *个人-流程发起数据访问实现类
 * @author 应用引擎自动生成
 * @date 2017-1-22 09:45:34
 *
 */ 
@Repository("fLOW_STARTDao")
public class FLOW_STARTDaoImpl implements FLOW_STARTDao{
	ClassSQLWire classSQLWire;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//插入记录
	public int insert(List<FLOW_START> _fLOW_STARTList) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		int count = 0;
		try {
			count = this.executeSql(_fLOW_STARTList, "插入"); 
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//删除记录
	public int delete(List<FLOW_START> _fLOW_STARTList) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		int count = 0;
		try {
			count = this.executeSql(_fLOW_STARTList, "删除");
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//更新记录
	public int update(List<FLOW_START> _fLOW_STARTList) 
			throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		int count = 0;
		try {
			count = this.executeSql(_fLOW_STARTList, "更新");
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	//使用对象检索返回List
	public List<FLOW_START> select(FLOW_START _fLOW_START) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		List<FLOW_START> fLOW_STARTList = null;
		String sql;

		sql = this.classSQLWire.ClassSQLSelect(fLOW_STARTList);
		ClassRowsMapper<FLOW_START> classRowsMapper = new ClassRowsMapper<FLOW_START>(FLOW_START.class);

		fLOW_STARTList = this.jdbcTemplate.query(sql, classRowsMapper);
		ClassSQLWire.printSQL(sql);
		return fLOW_STARTList;
	}

	//分页查询
	public List<FLOW_START> selectByPage(FLOW_START _fLOW_START, Integer page, Integer rows) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List<FLOW_START> fLOW_STARTList = null;
		String sql; 
		
		sql = this.classSQLWire.ClassSQLSelectByPage(fLOW_STARTList);
		ClassRowsMapper<FLOW_START> classRowsMapper = new ClassRowsMapper<FLOW_START>(FLOW_START.class);
		fLOW_STARTList = this.jdbcTemplate.query(sql, classRowsMapper);
		ClassSQLWire.printSQL(sql);
		
		return fLOW_STARTList;
	}

	//查询记录行数
	@SuppressWarnings("deprecation")
	public Integer selectCount(FLOW_START _fLOW_START) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String sql;

		sql = this.classSQLWire.ClassSQLSelectCount(_fLOW_START);
		ClassSQLWire.printSQL(sql);
		return this.jdbcTemplate.queryForInt(sql);
	}

	//自由条件查询
	public List<FLOW_START> queryUserDefineCondition(String sql) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String sqlZ;
		List<FLOW_START> fLOW_STARTList;
		FLOW_START flow_start = new FLOW_START();
		
		sqlZ = this.classSQLWire.ClassSQLSelectUserDefineCondition(flow_start,sql);
		ClassRowsMapper<FLOW_START> classRowsMapper = new ClassRowsMapper<FLOW_START>(FLOW_START.class);
		fLOW_STARTList = this.jdbcTemplate.query(sql,classRowsMapper);
		ClassSQLWire.printSQL(sqlZ);
		
		return fLOW_STARTList;
	}
	
	//自由条件查询分页
	public List<FLOW_START> queryUserDefineConditionByPage(String sql, Integer page, Integer rows) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String sqlZ;
		List<FLOW_START> fLOW_STARTList;
		FLOW_START flow_start = new FLOW_START();
		
		sqlZ = this.classSQLWire.ClassSQLSelectUserDefineConditionByPage(flow_start,sql,page,rows);
		ClassRowsMapper<FLOW_START> classRowsMapper = new ClassRowsMapper<FLOW_START>(FLOW_START.class);
		fLOW_STARTList = this.jdbcTemplate.query(sql,classRowsMapper);
		ClassSQLWire.printSQL(sqlZ);
		
		return fLOW_STARTList;
	}

	// 自定义SQL查询,需传入一整个SQL，但需注意规则：select 列名 as 实体属性名,...... from 表名。
	//表名可不是该实体，但结果集别名必须与实体属性名称一致。
	public List<FLOW_START> queryForUserDefineSQL(String sql, FLOW_START _fLOW_START) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		//TODO
		return null;
	}

	//自定义条件查询 查询记录条数 实现函数
	public Integer queryForUserDefineSQLCount(String sql) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String sqlZ;

		if (Tools.StringIsNullOrSpace(sql)) {
			sqlZ = "select count(1) from ECPJ_SBJCXXK ";
		} else {
			sqlZ = "select count(1) from ECPJ_SBJCXXK " + sql;
		}
		
		ClassSQLWire.printSQL(sql);
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 执行新增，修改，查询sql
	 * @param _fLOW_STARTList
	 * @return
	 */
	private int executeSql(List<FLOW_START> _fLOW_STARTList,String type) throws RuntimeException {
		int count = 0;
		List<String> sqlList = null;
		//数据校验
		if(_fLOW_STARTList == null || _fLOW_STARTList.size() == 0)
			throw new RuntimeException(type+"记录时输入的List不能为空，且长度不能为0！");
		for (int i = 0; i < _fLOW_STARTList.size(); i++) {
			if("java.lang.Integer".equals(_fLOW_STARTList.get(i).getId().getClass().getName())) 
			{
				DeAnnotaion annotation = (DeAnnotaion)FLOW_START.class.getAnnotations()[0]; //获取属性上的注解判断是否是自增
				if(!annotation.is_autoincrement() && _fLOW_STARTList.get(i).getId() == 0)
					throw new RuntimeException(type+"记录时VO的ID主键不能为空！"); 
			}
//			if("java.lang.String".equals(_fLOW_STARTList.get(i).getId().getClass().getName())){ //String ftl 判断生成不同分支
//				if (Tools.StringIsNullOrSpace(_fLOW_STARTList.get(i).getId())) {
//					throw new RuntimeException(type+"记录时VO的ID主键不能为空！");
//				}
//			}	
		}
		if("插入".equals(type))
			sqlList = this.classSQLWire.BatchClassSQLInsert(_fLOW_STARTList);
		else if("删除".equals(type))
			sqlList = this.classSQLWire.BatchClassSQLDelete(_fLOW_STARTList);
		else if("更新".equals(type))	
			sqlList = this.classSQLWire.BatchClassSQLUpdate(_fLOW_STARTList);
		if(sqlList == null || sqlList.size() ==0) throw new RuntimeException("生成的"+type+"SQL为空，请检查");
		ClassSQLWire.printSQL("成功生成 "+sqlList.size()+" 条，"+type+"SQL语句。");
		for (String sql : sqlList) {
			count ++;
			ClassSQLWire.printSQL(sql); //输出SQL日志
			this.jdbcTemplate.execute(sql); //执行sql
		}
		return count;
	}
}
