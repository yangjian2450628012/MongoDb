package www.xiaoyang;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiaoyang.entity.Zmenu;
import com.xiaoyang.util.ClassRowsMapper;

/** 
* @ClassName: MainTest 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class MainTest extends Aaa{
	@Autowired
	private JdbcTemplate jdbctemplate;
	@Test
	public void run(){
		//测试
	}

	@Override
	@Test
	public void say() {
		System.out.println("猫叫：喵喵...");  
	}
	
	@Test
	public void testDb(){
		this.jdbctemplate.queryForList("select * from admin");
	}
	
	@Test
	public void TestClassMapper(){
		String sql = "select * from menu order by orderA asc";
		ClassRowsMapper classMapper = new ClassRowsMapper(Zmenu.class);
		List<Zmenu> list = (List<Zmenu>)this.jdbctemplate.query(sql, classMapper);
		for (Zmenu zmenu : list) {
			System.out.print(zmenu.getId()+" ");
			System.out.print(zmenu.getText()+" ");
			System.out.print(zmenu.getCreatetime()+" ");
			System.out.print(zmenu.getOrderA());
			System.out.println();
		}
	}
}

abstract class Aaa {
	public abstract void say();
}