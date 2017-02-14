package www.xiaoyang;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import freemarker.template.Configuration;
import freemarker.template.Template;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class Aem_EneityTest {

	@Test
	public void testFtl(){
		Map<String,Object> map = new HashMap<String, Object>();
		
		String tableInfo = "{\"fnMenuName\":\"lcfq\",\"subclass\":\"流程测试表\",\"entityName\":\"FLOW_START2\",\"remark\":\"个人-流程发起\",\"create\":\"1\",\"createtime\":\"2017-01-22 23:04:40.0\",\"aem_entityId\":\"1DEDER\",\"package_id\":\"11B1FB\"}";
		JSONObject tableObject = JSONObject.fromObject(tableInfo);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("nowDate", sf.format(new Date()));
		
		map.put("ent", tableObject);
		
		String data = "[{\"TABLE_NAME\":\"FLOW_START2\",\"PRIMARYKEYS\":\"YES\",\"REMARKS\":\"主键id\",\"TYPE_NAME\":\"INT\",";
		data += "\"IS_AUTOINCREMENT\":\"YES\",\"COLUMN_NAME\":\"id\",\"SOURCE_DATA_TYPE\":\"java.lang.Integer\",\"IS_NULLABLE\":\"NO\"},{"; 
		data += "\"TABLE_NAME\":\"FLOW_START2\",\"PRIMARYKEYS\":\"NO\",\"REMARKS\":\"学生姓名\",\"TYPE_NAME\":\"VARCHAR\",";
		data += "\"IS_AUTOINCREMENT\":\"NO\",\"COLUMN_NAME\":\"name\",\"SOURCE_DATA_TYPE\":\"java.lang.String\",\"IS_NULLABLE\":\"YES\"}]";
		
		JSONArray dataArray = JSONArray.fromObject(data);
		//map.put("deAnnotaion", "@DeAnnotaion(is_autoincrement=true)//自增,主键");
		map.put("fldList", dataArray);
		
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding( "UTF-8" );
		//configuration.setServletContextForTemplateLoading( getServletContext(), "/" ); //FTL文件所存在的位置
		configuration.setClassForTemplateLoading(Aem_EneityTest.class, "/config/appEngine/");
		Template template = null;
		File file = null;
		FileOutputStream output = null;
		Writer out = null;
		boolean res = true;
		try {
			template = configuration.getTemplate("ame_entity.ftl");
			file = new File("D:/ftl/"+tableObject.getString("entityName")+".java");
			output = new FileOutputStream(file);
			out = new BufferedWriter( new OutputStreamWriter( output ) );
			
		} catch (IOException e) {
			e.printStackTrace();
			res = false;
			throw new RuntimeException("文件目录不正确请检查！");
		}
		
		try {
			template.setAutoFlush(false);
			template.process( map, out );
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
			throw new RuntimeException("生成java错误，请检查数据格式!");
		} finally {
			try {
				if(out != null)out.close();
				if(output != null)output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(!res){
				if(file.exists())file.delete(); //删除文件
				System.out.println("数据异常，删除文件");
			}
		}
	}
	
	/**
	 * 
	 * @param tableInfo 
	 * @param data
	 * @param base_path E:/JAVA/eclipse-Tool/workspaces/NICEOA
	 * @param packageN lcfq
	 * @param ftlName ame_entity.ftl
	 * @return
	 */
	private boolean createEntityFile(String tableInfo,String data,String base_path,String packageN,String ftlName){
		Map<String,Object> datamap = null;
		Template template = null;
		FileOutputStream output = null;
		Writer out = null;
		File realDir = null;
		File pathDir = null;
		String realpath;
		String path;
		boolean res = true;
		try {
			realpath = base_path+"/src/main/java/com/xiaoyang/entity/"+packageN;
			System.out.println("包路径为:"+realpath);
			//首先entity创建目录
			realDir = new File(realpath);
			if(!realDir.exists()){ 
				if(!realDir.mkdirs()) {
					res = false;
					throw new RuntimeException("创建包路径时出错，请检查项目目录结构!");
				}
			}
			datamap = new HashMap<String, Object>();
			
			JSONObject tableObject = JSONObject.fromObject(tableInfo);  //实体主信息
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //获取当前时间
			datamap.put("nowDate", sf.format(new Date()));
			datamap.put("ent", tableObject); //实体信息放置到map中
			
			JSONArray dataArray = JSONArray.fromObject(data); //属性列表
			datamap.put("fldList", dataArray);
			
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding( "UTF-8" );
			//configuration.setServletContextForTemplateLoading( getServletContext(), "/" ); //FTL文件所存在的位置
			configuration.setClassForTemplateLoading(Aem_EneityTest.class, "/config/appEngine/");
			template = configuration.getTemplate("ame_entity.ftl");
			
			path = base_path+"/src/main/java/com/xiaoyang/entity/"+packageN+"/"+tableObject.getString("entityName")+".java";
			System.out.println("实体文件目录为:"+path);
			//创建文件
			pathDir = new File(path);
			if(!pathDir.exists()){
				if(pathDir.createNewFile()){
					res = false;
					throw new RuntimeException("创建实体文件时出错，请检查目录结构!");
				}
			}
			output = new FileOutputStream(pathDir);
			out = new BufferedWriter( new OutputStreamWriter( output ) );
			template.process( datamap, out );
			output.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
			throw new RuntimeException("创建实体错误！");
		}finally {
			try {
				if(out != null)out.close();
				if(output != null)output.close();
			} catch (Exception e2) {
				res = false;
			}
			if(!res){
				int files = pathDir.getParentFile().listFiles().length;
				if(files > 1){ 
					pathDir.delete();
				}else{
					File dirP = realDir.getParentFile();
					File[] dirs = dirP.listFiles();
					for (File file : dirs) {
						if(packageN.equals(file.getName())){
							deleteDir(file); 
						}
					}
				}
			}
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		String base_path = "D:/ftl";
		String packageN = "lcfq";
		String entityname = "FLOW_START";
		String realpath = "";
		String path = "";
		File dir = null;
		File mkdir = null;
		boolean res = true;
		try {
			realpath = base_path+"/src/main/java/com/xiaoyang/entity/"+packageN;
			System.out.println("包路径:"+realpath);
			//首先创建目录
			dir = new File(realpath);
			//目录未存在
			if(!dir.exists()){ 
				if(!dir.mkdirs()) {
					res = false;
					throw new RuntimeException("创建包路径时出错，请检查项目目录结构!");
				}
			}
			
			path = base_path+"/src/main/java/com/xiaoyang/entity/"+packageN+"/"+entityname+".java";
			System.out.println("文件的目录为:"+path);
			//创建文件
			mkdir = new File(path);
			if(!mkdir.exists()){
				mkdir.createNewFile();
			}
			int i = 1 / 0; //故意抛异常
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
			throw new RuntimeException("创建目录出错！");
		}finally {
			//判断除异常，删除目录下的文件
			if(!res){
				System.out.println("程序异常删除目录文件");
				//首先判断是不是当前这个文件
				int files = mkdir.getParentFile().listFiles().length;
				if(files > 1){ 
					mkdir.delete(); //删除自己
				}else{
					//递归全部删除
					File dirP = dir.getParentFile(); //上级目录
					File[] dirs = dirP.listFiles();
					for (File file : dirs) {
						if(packageN.equals(file.getName())){ //调用递归删除目录文件
							deleteDir(file); //调用递归删除目录
						}
					}
				}
			}
		}
	}
	
	/**
	 * 递归删除目录
	 * @param file
	 */
	private static void deleteDir(File file){
		if(file.isDirectory()){ //判断是否是目录
			File[] files =  file.listFiles();
			for (File file2 : files) {
				deleteDir(file2);
			}
		}
		file.delete();
	}
}
