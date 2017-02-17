package com.xiaoyang.service.Impl.wdsq;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoyang.entity.wdsq.SQGL_ZXX;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * SQGL_ZXXService
 *申请管理信息服务接口类
 * @author 应用引擎自动生成
 * @date 2017-02-14 21:23:36
 *
 */ 
 public interface SQGL_ZXXService {
 	/**
	 * 删除函数
	 * @param List<SQGL_ZXX>
	 * @return int 返回删除记录行数
	 */
	public int sQGL_ZXX_delete(List<SQGL_ZXX>  _deleteSQGL_ZXXList);

	/**功能：查询 不分页,不建议使用。
	*  建议使用 queryByPage(SQGL_ZXX _sQGL_ZXX,Integer page,Integer rows),将 分页信息page,rows设置为0即可。
    * @param SQGL_ZXX
	* @return int 返回查询记录行数
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SQGL_ZXX> sQGL_ZXX_query(SQGL_ZXX _sQGL_ZXX);

	/**功能：分页查询，建议使用。
	* @param SQGL_ZXX,Integer,Integer
	* @return List<SQGL_ZXX> 返回查询记录动态对象数组
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SQGL_ZXX> sQGL_ZXX_queryByPage(SQGL_ZXX _sQGL_ZXX,Integer page,Integer rows);

	/**功能：查询记录条数
	* @param SQGL_ZXX
	* @return Integer 返回查询记录条数
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Integer sQGL_ZXX_queryCount(SQGL_ZXX _sQGL_ZXX);

	/**功能：插入对象
	* @param List<SQGL_ZXX>
	* @return int 返回 插入 记录条数
	*/
	public int sQGL_ZXX_save(List<SQGL_ZXX>  _saveSQGL_ZXXList);

	/**功能：修改对象
	* @param List<SQGL_ZXX>
	* @return int 返回 修改 记录条数
	*/
	public int sQGL_ZXX_update(List<SQGL_ZXX>  _updateSQGL_ZXXList) ;

	/**功能：自由条件查询 不分页，不建议使用。
	*  建议使用 queryUserDefineConditionByPage(String sql,Integer page,Integer rows),将 page,rows 分页信息设置为空即可。
	* @param SQGL_ZXX
	* @return List<SQGL_ZXX> 返回查询记录动态对象数组
	*/
	public List<SQGL_ZXX> sQGL_ZXX_queryUserDefineCondition(String sql);

	/**功能：自由条件查询分页
	* @param sql,page rows
	* @return List<SQGL_ZXX> 返回查询记录动态对象数组
	*/
	public List<SQGL_ZXX> sQGL_ZXX_queryUserDefineConditionByPage(String sql,Integer page,Integer rows);

}	