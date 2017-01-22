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
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
			e.printStackTrace();
			throw new AeMustRollBackException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.xiaoyang.service.Impl.lcfq.FLOW_STARTService#fLOW_START_query(com.xiaoyang.entity.lcfq.FLOW_START)
	 */
	public List<FLOW_START> fLOW_START_query(FLOW_START _fLOW_START) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xiaoyang.service.Impl.lcfq.FLOW_STARTService#fLOW_START_queryByPage(com.xiaoyang.entity.lcfq.FLOW_START, java.lang.Integer, java.lang.Integer)
	 */
	public List<FLOW_START> fLOW_START_queryByPage(FLOW_START _fLOW_START, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xiaoyang.service.Impl.lcfq.FLOW_STARTService#fLOW_START_queryCount(com.xiaoyang.entity.lcfq.FLOW_START)
	 */
	public Integer fLOW_START_queryCount(FLOW_START _fLOW_START) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xiaoyang.service.Impl.lcfq.FLOW_STARTService#fLOW_START_save(java.util.List)
	 */
	public int fLOW_START_save(List<FLOW_START> _saveFLOW_STARTList) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.xiaoyang.service.Impl.lcfq.FLOW_STARTService#fLOW_START_update(java.util.List)
	 */
	public int fLOW_START_update(List<FLOW_START> _updateFLOW_STARTList) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.xiaoyang.service.Impl.lcfq.FLOW_STARTService#fLOW_START_queryUserDefineCondition(java.lang.String)
	 */
	public List<FLOW_START> fLOW_START_queryUserDefineCondition(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xiaoyang.service.Impl.lcfq.FLOW_STARTService#fLOW_START_queryUserDefineConditionByPage(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public List<FLOW_START> fLOW_START_queryUserDefineConditionByPage(String sql, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xiaoyang.service.Impl.lcfq.FLOW_STARTService#eCPJ_SBJCXXK_queryUserDefineConditionCount(java.lang.String)
	 */
	public Integer eCPJ_SBJCXXK_queryUserDefineConditionCount(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	public FLOW_STARTDao getfLOW_STARTDao() {
		return fLOW_STARTDao;
	}
	@Autowired
	public void setfLOW_STARTDao(FLOW_STARTDao fLOW_STARTDao) {
		this.fLOW_STARTDao = fLOW_STARTDao;
	}
}
