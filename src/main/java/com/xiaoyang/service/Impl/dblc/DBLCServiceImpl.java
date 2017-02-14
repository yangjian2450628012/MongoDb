package com.xiaoyang.service.Impl.dblc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoyang.dao.dblc.DBLCDao;
import com.xiaoyang.entity.dblc.DBLC;
import com.xiaoyang.util.system.AeMustRollBackException;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * DBLCServiceImpl
 * 待办流程测试生成实体服务实现类
 * @author 应用引擎自动生成
 * @date 2017-02-06 15:22:09
 *
 */ 
@Service("dBLCService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false,rollbackFor = AeMustRollBackException.class)
public class DBLCServiceImpl implements DBLCService{
	// 声明DAO属性
	private DBLCDao dBLCDao;

	/**删除函数
	 * 
	 * @param List<DBLC>
	 * @return int 返回删除记录行数
	 */
	public int dBLC_delete(List<DBLC> _deleteDBLCList) {
		
		try {
			
			if ( _deleteDBLCList == null) {
				_deleteDBLCList= new ArrayList<DBLC>();
			}
			return this.dBLCDao.delete(_deleteDBLCList);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：查询 不分页,不建议使用。
	*  建议使用 queryByPage(DBLC dBLC ,Integer page,Integer rows),将 分页信息page,rows设置为0即可。
    * @param DBLC 
	* @return int 返回查询记录行数
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DBLC> dBLC_query(DBLC _dBLC ) {
		
		try {
			
			if ( _dBLC  == null) {
				_dBLC = new DBLC();
			}
			return this.dBLCDao.select(_dBLC );
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：分页查询，建议使用。
	* @param DBLC,page,rows
	* @return List<DBLC> 返回查询记录动态对象数组
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DBLC> dBLC_queryByPage(DBLC _dBLC, Integer page, Integer rows) {
		
		try {
			
			if (_dBLC == null) {
				_dBLC = new DBLC();
			}
			return this.dBLCDao.selectByPage(_dBLC, page, rows);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：查询记录条数
	* @param DBLC
	* @return Integer 返回查询记录条数
	*/
	public Integer dBLC_queryCount(DBLC _dBLC) {
		
		try {
			
			if (_dBLC == null) {
				_dBLC = new DBLC();
			}
			return this.dBLCDao.selectCount(_dBLC);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：插入对象
	* @param List<DBLC>
	* @return int 返回 插入 记录条数
	*/
	public int dBLC_save(List<DBLC> _saveDBLCList) {
		
		try {
			
			if (_saveDBLCList == null) {
				_saveDBLCList = new ArrayList<DBLC>();
			}
			return this.dBLCDao.insert(_saveDBLCList);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：修改对象
	* @param List<DBLC>
	* @return int 返回 修改 记录条数
	*/
	public int dBLC_update(List<DBLC> _updateDBLCList) {
		
		try {
			
			if (_updateDBLCList == null) {
				_updateDBLCList = new ArrayList<DBLC>();
			}
			return this.dBLCDao.update(_updateDBLCList);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：自由条件查询 不分页，不建议使用。
	*  建议使用 queryUserDefineConditionByPage(String sql,Integer page,Integer rows),将 page,rows 分页信息设置为空即可。
	* @param DBLC
	* @return List<DBLC> 返回查询记录动态对象数组
	*/
	public List<DBLC> dBLC_queryUserDefineCondition(String sql) {
		try {
			
			return this.dBLCDao.queryUserDefineCondition(sql);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：自由条件查询分页
	* @param sql,page rows
	* @return List<DBLC> 返回查询记录动态对象数组
	*/
	public List<DBLC> dBLC_queryUserDefineConditionByPage(String sql, Integer page, Integer rows) {

		try {
		
			return this.dBLCDao.queryUserDefineConditionByPage(sql,page,rows);
			
		} catch (Exception e) {
		
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：自定义SQL查询记录条数,返回Integer，记录条数
	* sql 为用户定义sql条件，如果全部查询请填写null
    * @param extParm
	* @return Integer 记录条数
	*/
	public Integer dBLC_queryUserDefineConditionCount(String sql) {
		try {

			return this.dBLCDao.queryForUserDefineSQLCount(sql);

		} catch (Exception e) {

			throw new AeMustRollBackException(e.getMessage());
		}
	}

	public DBLCDao getdBLCDao() {
		return dBLCDao;
	}
	@Autowired
	public void setdBLCDao(DBLCDao dBLCDao) {
		this.dBLCDao = dBLCDao;
	}
}
