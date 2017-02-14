package com.xiaoyang.service.Impl.system;

/** 
* @ClassName: AppEngineService 
* @Description: 引用引擎接口类
* @author yang
* @date 2017年1月23日 上午10:42:40 
*  
*/
public interface AppEngineService {

	/**
	 * 创建实体代码
	 * @param tableInfo 实体主信息
	 * @param data 实体属性
	 * @return
	 */
	boolean createDataMoudle(String tableInfo,String data);
}
