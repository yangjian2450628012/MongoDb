package com.xiaoyang.util.system;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xiaoyang.entity.lcfq.FLOW_START;

/** 
* @ClassName: ClassSQLWire 
* @Description: 拼装sql类
* @author yang
* @date 2017年1月22日 下午2:32:23 
*  
*/
public class ClassSQLWire {
	 protected static Log myLog = LogFactory.getLog(ClassSQLWire.class);
	/**
	 * 拼装插入sql
	 * @param _fLOW_STARTList 数据集合
	 * @return 插入sql
	 */
	public List<String> BatchClassSQLInsert(List<FLOW_START> _fLOW_STARTList){
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 打印sql语句
	 * @param _sql
	 */
	public static void printSQL(String _sql){
        myLog.debug((new StringBuilder()).append("AEM_SQL_LOG================").append(_sql).toString());
    }
	/** 拼装删除sql
	 * @param _fLOW_STARTList
	 * @return
	 */
	public List<String> BatchClassSQLDelete(List<FLOW_START> _fLOW_STARTList) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 拼装更新sql语句
	 * @param _fLOW_STARTList
	 * @return
	 */
	public List<String> BatchClassSQLUpdate(List<FLOW_START> _fLOW_STARTList) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 拼装使用对象检索返回List的sql语句
	 * @param fLOW_STARTList
	 * @return
	 */
	public String ClassSQLSelect(List<FLOW_START> fLOW_STARTList) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 拼装分页查询sql语句
	 * @param fLOW_STARTList
	 * @return
	 */
	public String ClassSQLSelectByPage(List<FLOW_START> fLOW_STARTList) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 拼装查询记录行数sql语句
	 * @param _fLOW_START
	 * @return
	 */
	public String ClassSQLSelectCount(FLOW_START _fLOW_START) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 拼装自由条件查询sql语句
	 * @param flow_start
	 * @param sql
	 * @return
	 */
	public String ClassSQLSelectUserDefineCondition(FLOW_START flow_start, String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 拼装自由条件查询分页sql语句
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 */
	public String ClassSQLSelectUserDefineConditionByPage(FLOW_START flow_start,String sql, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}
}
