package com.xiaoyang.service.Impl.dblc;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoyang.entity.dblc.DBLC;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * DBLCService
 *待办流程测试生成实体服务接口类
 * @author 应用引擎自动生成
 * @date 2017-02-06 15:22:09
 *
 */ 
 public interface DBLCService {
 	/**
	 * 删除函数
	 * @param List<DBLC>
	 * @return int 返回删除记录行数
	 */
	public int dBLC_delete(List<DBLC>  _deleteDBLCList);

	/**功能：查询 不分页,不建议使用。
	*  建议使用 queryByPage(DBLC _dBLC,Integer page,Integer rows),将 分页信息page,rows设置为0即可。
    * @param DBLC
	* @return int 返回查询记录行数
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DBLC> dBLC_query(DBLC _dBLC);

	/**功能：分页查询，建议使用。
	* @param DBLC,Integer,Integer
	* @return List<DBLC> 返回查询记录动态对象数组
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DBLC> dBLC_queryByPage(DBLC _dBLC,Integer page,Integer rows);

	/**功能：查询记录条数
	* @param DBLC
	* @return Integer 返回查询记录条数
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Integer dBLC_queryCount(DBLC _dBLC);

	/**功能：插入对象
	* @param List<DBLC>
	* @return int 返回 插入 记录条数
	*/
	public int dBLC_save(List<DBLC>  _saveDBLCList);

	/**功能：修改对象
	* @param List<DBLC>
	* @return int 返回 修改 记录条数
	*/
	public int dBLC_update(List<DBLC>  _updateDBLCList) ;

	/**功能：自由条件查询 不分页，不建议使用。
	*  建议使用 queryUserDefineConditionByPage(String sql,Integer page,Integer rows),将 page,rows 分页信息设置为空即可。
	* @param DBLC
	* @return List<DBLC> 返回查询记录动态对象数组
	*/
	public List<DBLC> dBLC_queryUserDefineCondition(String sql);

	/**功能：自由条件查询分页
	* @param sql,page rows
	* @return List<DBLC> 返回查询记录动态对象数组
	*/
	public List<DBLC> dBLC_queryUserDefineConditionByPage(String sql,Integer page,Integer rows);

}	