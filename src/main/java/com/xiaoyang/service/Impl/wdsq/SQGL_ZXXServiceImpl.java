package com.xiaoyang.service.Impl.wdsq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoyang.dao.wdsq.SQGL_ZXXDao;
import com.xiaoyang.entity.wdsq.SQGL_ZXX;
import com.xiaoyang.util.system.AeMustRollBackException;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * SQGL_ZXXServiceImpl
 * 申请管理信息服务实现类
 * @author 应用引擎自动生成
 * @date 2017-02-14 21:23:36
 *
 */ 
@Service("sQGL_ZXXService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false,rollbackFor = AeMustRollBackException.class)
public class SQGL_ZXXServiceImpl implements SQGL_ZXXService{
	// 声明DAO属性
	private SQGL_ZXXDao sQGL_ZXXDao;

	/**删除函数
	 * 
	 * @param List<SQGL_ZXX>
	 * @return int 返回删除记录行数
	 */
	public int sQGL_ZXX_delete(List<SQGL_ZXX> _deleteSQGL_ZXXList) {
		
		try {
			
			if ( _deleteSQGL_ZXXList == null) {
				_deleteSQGL_ZXXList= new ArrayList<SQGL_ZXX>();
			}
			return this.sQGL_ZXXDao.delete(_deleteSQGL_ZXXList);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：查询 不分页,不建议使用。
	*  建议使用 queryByPage(SQGL_ZXX sQGL_ZXX ,Integer page,Integer rows),将 分页信息page,rows设置为0即可。
    * @param SQGL_ZXX 
	* @return int 返回查询记录行数
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SQGL_ZXX> sQGL_ZXX_query(SQGL_ZXX _sQGL_ZXX ) {
		
		try {
			
			if ( _sQGL_ZXX  == null) {
				_sQGL_ZXX = new SQGL_ZXX();
			}
			return this.sQGL_ZXXDao.select(_sQGL_ZXX );
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：分页查询，建议使用。
	* @param SQGL_ZXX,page,rows
	* @return List<SQGL_ZXX> 返回查询记录动态对象数组
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SQGL_ZXX> sQGL_ZXX_queryByPage(SQGL_ZXX _sQGL_ZXX, Integer page, Integer rows) {
		
		try {
			
			if (_sQGL_ZXX == null) {
				_sQGL_ZXX = new SQGL_ZXX();
			}
			return this.sQGL_ZXXDao.selectByPage(_sQGL_ZXX, page, rows);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：查询记录条数
	* @param SQGL_ZXX
	* @return Integer 返回查询记录条数
	*/
	public Integer sQGL_ZXX_queryCount(SQGL_ZXX _sQGL_ZXX) {
		
		try {
			
			if (_sQGL_ZXX == null) {
				_sQGL_ZXX = new SQGL_ZXX();
			}
			return this.sQGL_ZXXDao.selectCount(_sQGL_ZXX);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：插入对象
	* @param List<SQGL_ZXX>
	* @return int 返回 插入 记录条数
	*/
	public int sQGL_ZXX_save(List<SQGL_ZXX> _saveSQGL_ZXXList) {
		
		try {
			
			if (_saveSQGL_ZXXList == null) {
				_saveSQGL_ZXXList = new ArrayList<SQGL_ZXX>();
			}
			return this.sQGL_ZXXDao.insert(_saveSQGL_ZXXList);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：修改对象
	* @param List<SQGL_ZXX>
	* @return int 返回 修改 记录条数
	*/
	public int sQGL_ZXX_update(List<SQGL_ZXX> _updateSQGL_ZXXList) {
		
		try {
			
			if (_updateSQGL_ZXXList == null) {
				_updateSQGL_ZXXList = new ArrayList<SQGL_ZXX>();
			}
			return this.sQGL_ZXXDao.update(_updateSQGL_ZXXList);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：自由条件查询 不分页，不建议使用。
	*  建议使用 queryUserDefineConditionByPage(String sql,Integer page,Integer rows),将 page,rows 分页信息设置为空即可。
	* @param SQGL_ZXX
	* @return List<SQGL_ZXX> 返回查询记录动态对象数组
	*/
	public List<SQGL_ZXX> sQGL_ZXX_queryUserDefineCondition(String sql) {
		try {
			
			return this.sQGL_ZXXDao.queryUserDefineCondition(sql);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：自由条件查询分页
	* @param sql,page rows
	* @return List<SQGL_ZXX> 返回查询记录动态对象数组
	*/
	public List<SQGL_ZXX> sQGL_ZXX_queryUserDefineConditionByPage(String sql, Integer page, Integer rows) {

		try {
		
			return this.sQGL_ZXXDao.queryUserDefineConditionByPage(sql,page,rows);
			
		} catch (Exception e) {
		
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：自定义SQL查询记录条数,返回Integer，记录条数
	* sql 为用户定义sql条件，如果全部查询请填写null
    * @param extParm
	* @return Integer 记录条数
	*/
	public Integer sQGL_ZXX_queryUserDefineConditionCount(String sql) {
		try {

			return this.sQGL_ZXXDao.queryForUserDefineSQLCount(sql);

		} catch (Exception e) {

			throw new AeMustRollBackException(e.getMessage());
		}
	}

	public SQGL_ZXXDao getsQGL_ZXXDao() {
		return sQGL_ZXXDao;
	}
	@Autowired
	public void setsQGL_ZXXDao(SQGL_ZXXDao sQGL_ZXXDao) {
		this.sQGL_ZXXDao = sQGL_ZXXDao;
	}
}
