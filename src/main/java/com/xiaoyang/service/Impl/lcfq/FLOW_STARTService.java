package com.xiaoyang.service.Impl.lcfq;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoyang.entity.lcfq.FLOW_START;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * FLOW_STARTService
 *个人-流程发起 服务接口类
 * @author 应用引擎自动生成
 * @date 2017-1-22 09:45:34
 *
 */ 
public interface FLOW_STARTService {
	/**
	 * 删除函数
	 * @param ECPJ_SBJCXXK 设备基础信息库
	 * @return int 返回删除记录行数
	 */
	public int fLOW_START_delete(List<FLOW_START>  _deleteFLOW_STARTList);
	
	/**功能：查询 不分页,不建议使用。
	*  建议使用 queryByPage(FLOW_START _eCPJ_SBJCXXK,Integer page,Integer rows),将 分页信息page,rows设置为0即可。
    * @param _fLOW_START
	* @return int 返回查询记录行数
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FLOW_START> fLOW_START_query(FLOW_START _fLOW_START);
	
	/**功能：分页查询，建议使用。
	* @param FLOW_START,page,rows
	* @return List<FLOW_START> 返回查询记录动态对象数组
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FLOW_START> fLOW_START_queryByPage(FLOW_START _fLOW_START,Integer page,Integer rows);
	
	/**功能：查询记录条数
	* @param FLOW_START
	* @return Integer 返回查询记录条数
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Integer fLOW_START_queryCount(FLOW_START _fLOW_START);
	
	/**功能：插入对象
	* @param List<FLOW_START>
	* @return int 返回 插入 记录条数
	*/
	public int fLOW_START_save(List<FLOW_START>  _saveFLOW_STARTList);
	
	/**功能：修改对象
	* @param List<FLOW_START>
	* @return int 返回 修改 记录条数
	*/
	public int fLOW_START_update(List<FLOW_START>  _updateFLOW_STARTList) ;
	
	/**功能：自由条件查询 不分页，不建议使用。
	*  建议使用 queryUserDefineConditionByPage(String sql,Integer page,Integer rows),将 page,rows 分页信息设置为空即可。
	* @param FLOW_START
	* @return List<ECPJ_SBJCXXK> 返回查询记录动态对象数组
	*/
	public List<FLOW_START> fLOW_START_queryUserDefineCondition(String sql);
	
	/**功能：自由条件查询分页
	* @param sql,page rows
	* @return List<FLOW_START> 返回查询记录动态对象数组
	*/
	public List<FLOW_START> fLOW_START_queryUserDefineConditionByPage(String sql,Integer page,Integer rows);
	
	/**功能：自定义SQL查询记录条数,返回Integer，记录条数
	* sql 为用户定义sql条件，如果全部查询请填写null
    * @param extParm
	* @return Integer 记录条数
	*/
	public Integer eCPJ_SBJCXXK_queryUserDefineConditionCount(String  sql);
}
