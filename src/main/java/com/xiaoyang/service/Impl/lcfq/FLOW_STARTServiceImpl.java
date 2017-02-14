package com.xiaoyang.service.Impl.lcfq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoyang.dao.lcfq.FLOW_STARTDao;
import com.xiaoyang.entity.lcfq.FLOW_START;
import com.xiaoyang.util.system.AeMustRollBackException;

/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * FLOW_STARTServiceImpl
 *设备基础信息库 服务实现类
 * @author 应用引擎自动生成
 * @date 2015-11-07 09:45:34
 *
 */ 
@Service("fLOW_STARTService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false,rollbackFor = AeMustRollBackException.class)
public class FLOW_STARTServiceImpl implements FLOW_STARTService{
	// 声明DAO属性
	private FLOW_STARTDao fLOW_STARTDao;
	
	/**删除函数
	 * 
	 * @param _deleteFLOW_STARTList
	 * @return int 返回删除记录行数
	 */
	public int fLOW_START_delete(List<FLOW_START> _deleteFLOW_STARTList) {
		
		try {
			
			if ( _deleteFLOW_STARTList == null) {
				_deleteFLOW_STARTList= new ArrayList<FLOW_START>();
			}
			return this.fLOW_STARTDao.delete(_deleteFLOW_STARTList);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}
	
	/**功能：查询 不分页,不建议使用。
	*  建议使用 queryByPage(FLOW_START _fLOW_START,Integer page,Integer rows),将 分页信息page,rows设置为0即可。
    * @param _fLOW_START
	* @return int 返回查询记录行数
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FLOW_START> fLOW_START_query(FLOW_START _fLOW_START) {
		
		try {
			
			if ( _fLOW_START == null) {
				_fLOW_START= new FLOW_START();
			}
			return this.fLOW_STARTDao.select(_fLOW_START);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：分页查询，建议使用。
	* @param FLOW_START,page,rows
	* @return List<FLOW_START> 返回查询记录动态对象数组
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FLOW_START> fLOW_START_queryByPage(FLOW_START _fLOW_START, Integer page, Integer rows) {
		
		try {
			
			if (_fLOW_START == null) {
				_fLOW_START = new FLOW_START();
			}
			return this.fLOW_STARTDao.selectByPage(_fLOW_START, page, rows);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：查询记录条数
	* @param FLOW_START
	* @return Integer 返回查询记录条数
	*/
	public Integer fLOW_START_queryCount(FLOW_START _fLOW_START) {
		
		try {
			
			if (_fLOW_START == null) {
				_fLOW_START = new FLOW_START();
			}
			return this.fLOW_STARTDao.selectCount(_fLOW_START);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：插入对象
	* @param List<FLOW_START>
	* @return int 返回 插入 记录条数
	*/
	public int fLOW_START_save(List<FLOW_START> _saveFLOW_STARTList) {
		
		try {
			
			if (_saveFLOW_STARTList == null) {
				_saveFLOW_STARTList = new ArrayList<FLOW_START>();
			}
			return this.fLOW_STARTDao.insert(_saveFLOW_STARTList);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：修改对象
	* @param List<FLOW_START>
	* @return int 返回 修改 记录条数
	*/
	public int fLOW_START_update(List<FLOW_START> _updateFLOW_STARTList) {
		
		try {
			
			if (_updateFLOW_STARTList == null) {
				_updateFLOW_STARTList = new ArrayList<FLOW_START>();
			}
			return this.fLOW_STARTDao.update(_updateFLOW_STARTList);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：自由条件查询 不分页，不建议使用。
	*  建议使用 queryUserDefineConditionByPage(String sql,Integer page,Integer rows),将 page,rows 分页信息设置为空即可。
	* @param FLOW_START
	* @return List<FLOW_START> 返回查询记录动态对象数组
	*/
	public List<FLOW_START> fLOW_START_queryUserDefineCondition(String sql) {
		try {
			
			return this.fLOW_STARTDao.queryUserDefineCondition(sql);
			
		} catch (Exception e) {
			
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：自由条件查询分页
	* @param sql,page rows
	* @return List<FLOW_START> 返回查询记录动态对象数组
	*/
	public List<FLOW_START> fLOW_START_queryUserDefineConditionByPage(String sql, Integer page, Integer rows) {

		try {
		
			return this.fLOW_STARTDao.queryUserDefineConditionByPage(sql,page,rows);
			
		} catch (Exception e) {
		
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/**功能：自定义SQL查询记录条数,返回Integer，记录条数
	* sql 为用户定义sql条件，如果全部查询请填写null
    * @param extParm
	* @return Integer 记录条数
	*/
	public Integer eCPJ_SBJCXXK_queryUserDefineConditionCount(String sql) {
		try {

			return this.fLOW_STARTDao.queryForUserDefineSQLCount(sql);

		} catch (Exception e) {

			throw new AeMustRollBackException(e.getMessage());
		}
	}

	public FLOW_STARTDao getfLOW_STARTDao() {
		return fLOW_STARTDao;
	}
	@Autowired
	public void setfLOW_STARTDao(FLOW_STARTDao fLOW_STARTDao) {
		this.fLOW_STARTDao = fLOW_STARTDao;
	}
}
