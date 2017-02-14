package com.xiaoyang.util.system;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: ClassSQLWrite 
* @Description: 拼装sql类
* @author yang
* @date 2017年1月24日 上午9:52:51 
*  
*/
public class ClassSQLWrite {
	protected static Logger log = LoggerFactory.getLogger(ClassSQLWrite.class);
	
	public int batchClassSQLInsert(List<?> _insertList,List<String> sqlList,List<List> paramList){
		try {
			if(_insertList == null || _insertList.size() == 0)
				throw new AeMustRollBackException("需要保存的对象List不能为空 且元素个数不能为0，请检查_insertList.");
			if(sqlList == null)
				throw new AeMustRollBackException("sqlList 必须初始化，请检查sqlList.");
			sqlList.clear();
			if(paramList == null)
				throw new AeMustRollBackException("paramList 必须初始化，请检查paramList.");
			paramList.clear();
			Method[] methods = _insertList.get(0).getClass().getDeclaredMethods(); //通过反射获取实体所有方法
			List getMethodArr = new ArrayList();
			
			this.getFieldNameArr(methods, getMethodArr, null);
			String className = this.getClassName(_insertList.get(0));
			
			Map returnMap = new HashMap(); //key:get方法名,value：get方法返回值类型
			Method methodH;
			for (Iterator $i = getMethodArr.iterator();$i.hasNext();returnMap.put(methodH.getName(), methodH.getReturnType().getName()))
				methodH = (Method)$i.next();
				
			for(int i=0;i<_insertList.size();i++){
				Iterator $i = getMethodArr.iterator();
				StringBuffer fieldName = new StringBuffer();
				StringBuffer paramStringBuffer = new StringBuffer();
				List paramValueList = new ArrayList();
//				List returnType = new ArrayList();
				do {
					if(!$i.hasNext())break;
					Method method = (Method)$i.next();
					Object value = method.invoke(_insertList.get(i), null);
					//获取方法上的自定义注解
					DeAnnotaion deAnnotaion = method.getAnnotation(DeAnnotaion.class);
					if(!deAnnotaion.isNull() && value == null){
						throw new AeMustRollBackException((new StringBuilder()).append(method.getName().substring(3)).append("不能为空!").toString());
					}
					if(value != null){
						fieldName.append(method.getName().substring(3)).append(",");
						paramStringBuffer.append("?,");
						paramValueList.add(value);
					}
					
				} while (true);
				if(!Tools.StringIsNullOrSpace(fieldName.toString())){
					sqlList.add((new StringBuilder()).append("insert into ").append(className).append("(").append(fieldName.subSequence(0, fieldName.length()-1))
							.append(") values (").append(paramStringBuffer.substring(0, paramStringBuffer.length()-1)).append(")").toString());
					paramList.add(paramValueList);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AeMustRollBackException((new StringBuilder()).append("装配Insert SQL时发生错误，").append(e.getMessage()).toString());
		}
		return sqlList.size();
	}
	/** 拼装删除SQL
	 * @param _DeleteList
	 * @return
	 */
	public int batchClassSQLDelete(List<?> _DeleteList,List<String> sqlList,List<List> paramList) {
		try {
			 if(_DeleteList == null || _DeleteList.size() < 1)
	                throw new AeMustRollBackException("需要删除的对象List不能为空 且元素个数不能为0，请检查_TList.");
	         if(sqlList == null)
	                throw new AeMustRollBackException("SQLList 必须初始化，请检查sqlList.");
	            sqlList.clear();
	         if(paramList == null)
	                throw new AeMustRollBackException("参数List 必须初始化，请检查paramList.");
	            paramList.clear();
	         //主键为空与主键不为空得情况
	         Field field = _DeleteList.get(0).getClass().getDeclaredField("id");   
	         DeAnnotaion deAnnotaion = field.getAnnotation(DeAnnotaion.class);
	         String classname = this.getClassName(_DeleteList.get(0));
	         if(deAnnotaion.isPrimary()){
	        	 for (int i = 0; i < _DeleteList.size(); i++) {
	        		 List listParam = new ArrayList();
	        		 StringBuilder sqls = new StringBuilder();
	        		 StringBuilder paramValue = new StringBuilder();
	        		 
	        		 Class<? extends Object> eneityClass = _DeleteList.get(i).getClass();
	        		 Method method = eneityClass.getMethod("getId",null);
	        		 Object value = method.invoke(_DeleteList.get(i),null);
	        		 sqls.append("delete from ").append(classname).append(" where id = ?");
	        		 paramValue.append(value);
	        		 sqlList.add(sqls.toString());
	        		 listParam.add(paramValue.toString());
	        		 paramList.add(listParam);
				}
	         }else{
	        	 
	         }
	            
		} catch (Exception e) {
			e.printStackTrace();
			 throw new AeMustRollBackException((new StringBuilder()).append("装配Delete SQL时发生错误，").append(e.getMessage()).toString());
		}
		return sqlList.size();
	}
	
	
	/** 拼装更新sql语句
	 * @param _TList
	 * @return
	 */
	public List<String> BatchClassSQLUpdate(List<?> _TList) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 拼装使用对象检索返回List的sql语句
	 * @param TList
	 * @return
	 */
	public String ClassSQLSelect(List<?> TList) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 拼装分页查询sql语句
	 * @param TList
	 * @return
	 */
	public String ClassSQLSelectByPage(List<?> TList) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 拼装查询记录行数sql语句
	 * @param _T
	 * @return
	 */
	public String ClassSQLSelectCount(Object _T) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 拼装自由条件查询sql语句
	 * @param T
	 * @param sql
	 * @return
	 */
	public String ClassSQLSelectUserDefineCondition(Object T, String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 拼装自由条件查询分页sql语句
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 */
	public String ClassSQLSelectUserDefineConditionByPage(Object T,String sql, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 获取实体名
	 * @param object 实体
	 * @return
	 */
	private String getClassName(Object object) {
		Class<?> classs = object.getClass();
		return classs.getName().substring(classs.getName().lastIndexOf(".")+1);
	}

	/**
	 * @param methods 所有方法数组
	 * @param _getMethodArray 接收get方法数组
	 * @param fieldNameArr 接收所有属性名
	 */
	private void getFieldNameArr(Method[] methods,List _getMethodArray,List fieldNameArr){
		for (int i = 0; i < methods.length; i++) {
			if(methods[i].getName().startsWith("get")){
				_getMethodArray.add(methods[i]);
				if(fieldNameArr != null)fieldNameArr.add(methods[i].getName().substring(3));
			}
		}
	}
	/**
	 * 打印日志信息
	 * @param _sql
	 */
	public static void printSQL(String _sql){
        log.info((new StringBuilder()).append("AEM_SQL_LOG================").append(_sql).toString());
    }
}
