/*package com.xiaoyang.wdsq;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiaoyang.entity.wdsq.SQGL_ZXX;
import com.xiaoyang.service.Impl.wdsq.SQGL_ZXXService;
import com.xiaoyang.util.system.ClassSQLWrite;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class WDSQTest {
	@Autowired
	private SQGL_ZXXService sQGL_ZXXService;
	
	@Test
	public void insert(){
		List<SQGL_ZXX> list = new ArrayList<SQGL_ZXX>();
		SQGL_ZXX sqgl_zxx = new SQGL_ZXX();
		sqgl_zxx.setId("UUGUYF46567YRFRTUK");
		sqgl_zxx.setPid("OIJOIJONKNLLLMLLL");
		sqgl_zxx.setSqdid("JLKJLJOIHJKLLJLK");
		sqgl_zxx.setSqdname("小杨");
		list.add(sqgl_zxx);
		sqgl_zxx = new  SQGL_ZXX();
		sqgl_zxx.setId("UUGUYF46567YRF23K");
		sqgl_zxx.setPid("OIJOIJONKNLLL32LLL");
		sqgl_zxx.setSqdid("JLKJLJOIHJK32JLK");
		sqgl_zxx.setSqdname("小杨2");
		sqgl_zxx.setText("申请管理2");
		list.add(sqgl_zxx);
		int insertC = sQGL_ZXXService.sQGL_ZXX_save(list);
		ClassSQLWrite.printSQL("成功保存数据："+insertC+"条");
	}
}
*/