package com.xiaoyang.util.system;

/** 
* @ClassName: AeMustRollBackException 
* @Description: 事务回滚异常
* @author yang
* @date 2017年1月22日 下午5:22:27 
*  
*/
public class AeMustRollBackException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public AeMustRollBackException()
    {
    }
    public AeMustRollBackException(String _errMsg)
    {
        super(_errMsg);
    }
    public AeMustRollBackException(Exception e)
    {
        super(e);
    }
}
