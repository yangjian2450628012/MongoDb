package www.xiaoyang;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

class NUll{
	private static String $a = "6789";
	public static void haha(){
		System.out.println($a);
		System.out.println("hhh");
	}
	
	public static void main(String[] args) {
		new NUll().haha();
		
		((NUll)null).haha();
		
//		String s;
//		System.out.println("s="+s);
	}
}


/** 
* @ClassName: FdInterface 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yang
* @date 2017年2月16日 上午11:02:11 
*  
*/
public class FdInterface implements fd {

	public void say() {
		
	}

	/* (non-Javadoc)
	 * @see www.xiaoyang.fd#V()
	 */
	public void V(String c,int a) {
		System.out.println("参数c: "+c);
		System.out.println("int a的值为:"+a);
	}
	
	public static void main(String[] args) {
		/*FdInterface df = new FdInterface();
		System.out.println(df.name);*/
		
		//实例话对象的四种方法
		/*try {
			Class<?> c = Class.forName("www.xiaoyang.FdInterface");
			Field field = c.getField("name");
			System.out.println(field.get(null));
			
			Method method = c.getMethod("V", String.class,int.class);
			method.invoke(c.newInstance(),new Object[]{"哈哈测试",2});
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		*/
		
	}

}
