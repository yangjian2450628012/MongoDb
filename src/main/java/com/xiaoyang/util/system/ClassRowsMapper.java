package com.xiaoyang.util.system;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

/** 
* @ClassName: ClassRowsMapper 
* @Description: 数据结果集与实体映射
* @author YJ
* @date 2017年1月19日 下午6:21:21 
*  
*/
public class ClassRowsMapper<T> implements RowMapper<T> {
	private Log log;
    private Class<T> mapClass;
    private T returnObject;
    
    public ClassRowsMapper(Class<T> mapClass)
    {
        log = LogFactory.getLog(ClassRowsMapper.class);
        setMapClass(mapClass);
    }
	 /*
	  *数据查询结果与实体映射
	  */
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		String className = "";
        String fieldName = "";
        Map<Object,Object> methodMap = null;
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        if(colCount < 1)
            throw new RuntimeException("select语句语法错误，无结果集返回。");
        try{
        	className = mapClass.getName();
            returnObject = mapClass.newInstance();
            Method methodArray[] = returnObject.getClass().getMethods();
            methodMap = new HashMap<Object, Object>();
            for(int j = 0; j < methodArray.length; j++){
            	methodMap.put(methodArray[j].getName(), methodArray[j]);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        
        for(int i = 0; i < colCount; i++)
        {
        	fieldName = rsmd.getColumnName(i + 1);
        	if(fieldName.equalsIgnoreCase("rownum") || fieldName.equalsIgnoreCase("row_num") || fieldName.equalsIgnoreCase("rownumber"))
                continue;
            
            Method method = (Method)methodMap.get((new StringBuilder()).append("set").append(fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase())).toString());
            if(method == null)
            {
            	 log.error((new StringBuilder()).append("实体类:").append(className).append(" 缺少：set").append(fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase())).append("方法，请确认").toString());
                continue;
            }
            if(method.getParameterTypes() == null)
                throw new RuntimeException((new StringBuilder()).append("实体类:").append(className).append(" 缺少：set").append(fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase())).append("方法，入参为空，请查看实体类该set方法。").toString());
            if(method.getParameterTypes().length != 1)
                throw new RuntimeException((new StringBuilder()).append("实体类:").append(className).append(" 缺少：set").append(fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase())).append("方法，入参有且只有一个，请查看实体类该set方法。").toString());
            
            String methodParamType = method.getParameterTypes()[0].getName();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
            try
            {
                if(methodParamType.equals("java.lang.String") || methodParamType.equals("String"))
                {
                	DeAnnotaion annotation = (DeAnnotaion)method.getAnnotation(DeAnnotaion.class);
                    if(annotation == null || !annotation.isStringBlob())
                    {
                        method.invoke(returnObject, new Object[] {rs.getString(fieldName)});
                        continue;
                    }
                    if(annotation.isTimestamp())
                    {
                        method.invoke(returnObject, new Object[] {sdf.format(rs.getTimestamp(fieldName))});
                        continue;
                    }
                    if(annotation.isStringBlob())
                        method.invoke(returnObject, new Object[] {TypeConTools.ClobToStr(rs.getClob(fieldName))});
                    	continue;
                }
                if(methodParamType.equals("java.lang.Integer") || methodParamType.equals("Integer") || methodParamType.equals("int"))
                {
                    method.invoke(returnObject, new Object[] {Integer.valueOf(rs.getInt(fieldName))});
                    continue;
                }
                if(methodParamType.equals("java.lang.Float") || methodParamType.equals("Float") || methodParamType.equals("float"))
                {
                    method.invoke(returnObject, new Object[] {Float.valueOf(rs.getFloat(fieldName))});
                    continue;
                }
                if(methodParamType.equals("java.lang.Double") || methodParamType.equals("Double") || methodParamType.equals("double"))
                {
                    method.invoke(returnObject, new Object[] {Double.valueOf(rs.getDouble(fieldName))});
                    continue;
                }
                if(methodParamType.equals("java.sql.Clob") || methodParamType.equals("Clob"))
                {
                    method.invoke(returnObject, new Object[] {rs.getClob(fieldName)});
                    continue;
                }
                if(methodParamType.equals("java.sql.Blob") || methodParamType.equals("Blob"))
                    method.invoke(returnObject, new Object[] {rs.getBlob(fieldName)});
                else
                	throw new RuntimeException((new StringBuilder()).append("CommSingleClassRowsMapper：通用ORM类支持的数据类型为:String、Integer(int)、Float(float)、Double(double)、Clob、Blob，但输入的数据类型为：").append(methodParamType).toString());
            }
            catch(Exception e)
            {
                throw new RuntimeException(e);
            }
        }
		return returnObject;
	}
	public Class<?> getMapClass() {
		return mapClass;
	}
	public void setMapClass(Class<T> mapClass) {
		this.mapClass = mapClass;
	}
}
