package www.xiaoyang;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		try {
			this.jdbctemplate.queryForList("select * from admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateTable (){
		String db = "`niceoa`";
		String tables = "`NewTable`";
		String s1 = "`id`";
		String s2 = "`username`";
		String s3 = "`pwd`";
		String s4 = "`is`";
		String s5 = "`pid`";
		String s6 = "`createtime`";
		try {
			//存在就删除
			//this.jdbctemplate.execute("DROP TABLE IF EXISTS "+tables+"");
			StringBuilder sbr = new StringBuilder();
//			sbr.append("use ").append(db).append(" ");
//			sbr.append("DROP TABLE IF EXISTS ").append(tables).append(" ");
			sbr.append("CREATE TABLE ").append(tables).append(" (");
			//循环属性
			sbr.append(s1).append(" int(10)").append(" NOT NULL").append(" AUTO_INCREMENT").append(" COMMENT '主键id',");
			sbr.append(s2).append(" varchar(30)").append(" CHARACTER SET utf8").append(" NOT NULL").append(" DEFAULT 'xiaoyang'").append(" COMMENT '测试表用户名',");
			sbr.append(s3).append(" varchar(40)").append(" CHARACTER SET utf8").append(" NOT NULL").append(" DEFAULT '1q1q1q1q'").append(" COMMENT '测试表密码',");
			sbr.append(s4).append(" blob").append(" NOT NULL").append(" COMMENT '是否为空',");
			sbr.append(s5).append(" linestring").append(" NULL,");
			sbr.append(s6).append(" datetime").append(" NULL").append(" COMMENT '创建时间22',");
			
			//主键
			sbr.append("PRIMARY KEY ").append("(`id`, `pwd`),");
			//索引
			sbr.append("KEY ").append(" `my_username`").append(" (`username`)");
			sbr.append(")").append("ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			System.out.println(sbr.toString());
			//this.jdbctemplate.execute(sbr.toString()); //创建表
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
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
	
	@Test
	public void testInfo(){
		try {
			//获取数据库
			//String info = this.jdbctemplate.getDataSource().getConnection().getCatalog();
			this.jdbctemplate.queryForList("select * from admin");
//			ResultSet rs = this.jdbctemplate.getDataSource().getConnection().getMetaData().getTables(null, null, "admin", null);
			ResultSet rs =  this.jdbctemplate.getDataSource().getConnection().getMetaData().getPrimaryKeys(null, null, "admin");
//			ResultSet rs =  this.jdbctemplate.getDataSource().getConnection().getMetaData().getExportedKeys(null, null, "admin");
//			ResultSet rs =  this.jdbctemplate.getDataSource().getConnection().getMetaData().getSuperTables(null, null, "admin");
//			ResultSet rs =  this.jdbctemplate.getDataSource().getConnection().getMetaData().getColumns(null, null, "admin", null);
//			sys(rs);
			for (int i = 0; i < 3; i++) {
				while(rs.next()){ //获取主键值
					System.out.println(rs.getString("COLUMN_NAME"));
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sys(ResultSet rs) throws SQLException {
		ResultSetMetaData rm = rs.getMetaData();
		int columns = rm.getColumnCount();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> lista = new ArrayList<String>();
		//显示列,表格的表头
		for(int i=1;i<=columns;i++)
		{
			lista.add(rm.getColumnName(i));
		}
		   //显示表格内容
		while(rs.next())
		{
			Map<String,Object> map = new HashMap<String, Object>();
		  for(int i=1;i<columns;i++)
		  {
			  map.put( lista.get(i), rs.getString(lista.get(i)));
		  }
		  list.add(map);
		}
		System.out.println(list);
	}
	
	
	@Test
	public void queryT(){
		try {
			ResultSet rs = this.jdbctemplate.getDataSource().getConnection().getMetaData().getColumns(null, null, "admin", null);
			ResultSetMetaData rm = rs.getMetaData();
			int columns = rm.getColumnCount();
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			List<String> lista = new ArrayList<String>();
			//显示列,表格的表头
			for(int i=1;i<=columns;i++)
			{
				lista.add(rm.getColumnName(i));
			}
			   //显示表格内容
			while(rs.next())
			{
				Map<String,Object> map = new HashMap<String, Object>();
			  for(int i=1;i<columns;i++)
			  {
				  map.put( lista.get(i), rs.getString(i));
			  }
			  list.add(map);
			}
			System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

abstract class Aaa {
	public abstract void say();
}