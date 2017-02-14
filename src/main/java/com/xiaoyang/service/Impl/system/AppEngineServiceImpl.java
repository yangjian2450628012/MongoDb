package com.xiaoyang.service.Impl.system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyang.util.system.AeMustRollBackException;
import com.xiaoyang.util.system.ClassSQLWrite;

import freemarker.template.Configuration;
import freemarker.template.Template;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
* @ClassName: AppEngineServiceImpl 
* @Description:  引用引擎实现类
* @author yang
* @date 2017年1月23日 上午10:44:29 
*  
*/
@Service("appEngineService")
public class AppEngineServiceImpl implements AppEngineService {
	private AdminService adminService;
	
	/**
	 * 创建实体代码
	 * @param tableInfo 实体主信息
	 * @param data 实体属性
	 * @return
	 */
	public boolean createDataMoudle(String tableInfo, String data) {
		JSONObject tableObject = null;
		JSONArray dataArray = null;
		String base_path;
		boolean res = true;
		try {
			tableObject = JSONObject.fromObject(tableInfo);
			dataArray = JSONArray.fromObject(data);
			
			if(tableObject.get("packageN") == null || "".equals(tableObject.get("packageN"))){
				throw new RuntimeException("实体包名不能为空！");
			}
			if(tableObject.get("entityName") == null || "".equals(tableObject.get("entityName"))){
				throw new RuntimeException("实体名称不能为空！");
			}
			
			this.adminService.createEntityTable(data, tableInfo); //创建表
			
			
			Properties properties = new Properties();  //读取base_path
			InputStream in = AppEngineServiceImpl.class.getResourceAsStream("/system.properties");
			properties.load(in);
			in.close();
			base_path = properties.getProperty("base_path");
			if(!new File(base_path).exists()) throw new RuntimeException("项目工程根目录位置不正确!");
			
			res = createEntityFile(tableObject,dataArray,base_path+"/src/main/java/com/xiaoyang/entity","ame_entity.ftl",""); //创建entity
			if(!res) return res;
			
			res = createEntityFile(tableObject,null,base_path+"/src/main/java/com/xiaoyang/dao","aem_dao.ftl","Dao"); //创建dao
			if(!res) return res;
			
			JSONObject property = null;
			for (Object object : dataArray) {
				JSONObject d = JSONObject.fromObject(object);
				if("id".equals(d.getString("COLUMN_NAME").toLowerCase())){
					property = d ;
				}
			}
			res = createEntityFile(tableObject,property,base_path+"/src/main/java/com/xiaoyang/dao","aem_daoImpl.ftl","DaoImpl"); //创建daoImpl
			if(!res) return res;
			
			res = createEntityFile(tableObject,null,base_path+"/src/main/java/com/xiaoyang/service/Impl","aem_service.ftl","Service"); //创建service
			if(!res) return res;
			
			res = createEntityFile(tableObject,null,base_path+"/src/main/java/com/xiaoyang/service/Impl","aem_serviceImpl.ftl","ServiceImpl"); //创建serviceImpl
			if(!res) return res;
			
		} catch (Exception e) {
			res = false;
			e.printStackTrace();
			throw new AeMustRollBackException(e.getMessage());
		}
		return res;
	}

	/**
	 * 需要同步，防止文件操作失败
	 * @param tableInfo 
	 * @param data
	 * @param base_path E:/JAVA/eclipse-Tool/workspaces/NICEOA
	 * @param packageN lcfq
	 * @param ftlName ame_entity.ftl
	 * @return
	 */
	private synchronized boolean createEntityFile(JSONObject tableObject,Object dataArray,String realpath,String ftlName,String endWith) throws Exception{
		Map<String,Object> datamap = null;
		Template template = null;
		FileOutputStream output = null;
		Writer out = null;
		File realDir = null;
		File pathDir = null;
		String path;
		boolean res = true;
		try {
			realpath = realpath + "/" + tableObject.getString("packageN");
			ClassSQLWrite.printSQL(realpath);
			//首先entity创建目录
			realDir = new File(realpath);
			if(!realDir.exists()){ 
				if(!realDir.mkdirs()) {
					res = false;
					throw new RuntimeException("创建包路径时出错，请检查项目目录结构!");
				}
			}
			datamap = new HashMap<String, Object>();
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			datamap.put("nowDate", sf.format(new Date()));
			datamap.put("ent", tableObject); //实体信息放置到map中
			
			if(dataArray != null)datamap.put("fldList", dataArray); //属性放置到map中
			
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding( "UTF-8" );
			configuration.setClassForTemplateLoading(AppEngineServiceImpl.class, "/config/appEngine/");
			template = configuration.getTemplate(ftlName);
			
			path = realpath + "/" + tableObject.getString("entityName").substring(0, 1).toUpperCase() + tableObject.getString("entityName").substring(1) + endWith +".java";
			ClassSQLWrite.printSQL(path);
			//创建文件
			pathDir = new File(path);
			if(!pathDir.exists()){
				if(!pathDir.createNewFile()){
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
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				if(out != null)out.close();
				if(output != null)output.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				res = false;
				throw new RuntimeException(e2.getMessage());
			}
			if(!res){
				int files = pathDir.getParentFile().listFiles().length;
				if(files > 1){ 
					pathDir.delete();
				}else{
					File dirP = realDir.getParentFile();
					File[] dirs = dirP.listFiles();
					for (File file : dirs) {
						if(tableObject.getString("packageN").equals(file.getName())){
							deleteDir(file); 
						}
					}
				}
			}
		}
		return res;
	}
	/**
	 * 递归删除文件
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
	
	public AdminService getAdminService() {
		return adminService;
	}
	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
}
