package www.xiaoyang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* @ClassName: MainTest 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class MainTest extends Aaa{

	@Test
	public void run(){
		//测试
	}

	@Override
	@Test
	public void say() {
		System.out.println("猫叫：喵喵...");  
	}
}

abstract class Aaa {
	public abstract void say();
}