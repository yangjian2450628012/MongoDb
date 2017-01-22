package com.xiaoyang.dao.lcfq;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.xiaoyang.entity.lcfq.FLOW_START;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * FLOW_STARTDao
 *个人-流程发起数据访问接口
 * @author 应用引擎自动生成
 * @date 2017-1-22 09:45:34
 *
 */ 
public interface FLOW_STARTDao {
	
	/** 插入记录
	 * @param _fLOW_STARTList 要保存的数据集合
	 * @return 返回插入记录数
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public abstract int insert(List<FLOW_START> _fLOW_STARTList)
			throws IllegalArgumentException, IllegalAccessException,InvocationTargetException;
	/** 删除记录
	 * @param _fLOW_STARTList 要删除的数据集合
	 * @return 返回删除记录条数
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public abstract int delete(List<FLOW_START> _fLOW_STARTList)
			throws IllegalArgumentException, IllegalAccessException,InvocationTargetException;
	/** 更新记录
	 * @param _fLOW_STARTList 要更新的数据集合
	 * @return 返回更新影响记录数
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public abstract int update(List<FLOW_START> _fLOW_STARTList)
			throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException;
	/** 使用对象检索返回List
	 * @param _fLOW_START 实体对象
	 * @return 返回查询结果列表
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public abstract List<FLOW_START> select(FLOW_START _fLOW_START)
			throws IllegalArgumentException, IllegalAccessException,InvocationTargetException;
	/** 分页查询
	 * @param _fLOW_START 实体对象
	 * @param page 第几页
	 * @param rows 显示条数
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public abstract List<FLOW_START> selectByPage(FLOW_START _fLOW_START, Integer page,Integer rows)
			throws IllegalArgumentException, IllegalAccessException,InvocationTargetException;
	/**查询记录行数
	 * @param _fLOW_START 实体对象
	 * @return 返回记录条数
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public abstract Integer selectCount(FLOW_START _fLOW_START)
			throws IllegalArgumentException, IllegalAccessException,InvocationTargetException;
	/** 自定义条件查询
	 * @param sql 自定义sql结尾部分
	 * @return 返回查询结果的数据集合
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public abstract List<FLOW_START> queryUserDefineCondition(String sql)
			throws IllegalArgumentException, IllegalAccessException,InvocationTargetException;
	/** 自定义条件查询分页
	 * @param sql 自定义sql结尾部分
	 * @param page 第几页
	 * @param rows 显示条数
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<FLOW_START> queryUserDefineConditionByPage(String sql,Integer page,Integer rows)
			throws IllegalArgumentException, IllegalAccessException,InvocationTargetException;
	/** 自定义SQL查询,可用于复杂字典转换或多表查询
	 * @param sql 自定义sql
	 * @param _fLOW_START 实体对象
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public List<FLOW_START> queryForUserDefineSQL(String sql,FLOW_START _fLOW_START) 
			throws IllegalArgumentException,IllegalAccessException, InvocationTargetException,SecurityException, NoSuchMethodException;
	/** 自定义条件查询 查询记录条数 接口函数
	 * @param sql 自定义sql结尾部分
	 * @return 返回查询结果条数
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public abstract Integer queryForUserDefineSQLCount(String sql)
			throws IllegalArgumentException, IllegalAccessException,InvocationTargetException;
}
