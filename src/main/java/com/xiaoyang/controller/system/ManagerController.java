package com.xiaoyang.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.xiaoyang.entity.system.Admin;
import com.xiaoyang.entity.system.JsonTreeData;
import com.xiaoyang.entity.system.Menu;
import com.xiaoyang.entity.system.Submenu;
import com.xiaoyang.service.Impl.system.AdminService;
import com.xiaoyang.service.Impl.system.AppEngineService;
import com.xiaoyang.service.Impl.system.LoginService;
import com.xiaoyang.util.system.EasyuiResult;
import com.xiaoyang.util.system.Tools;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
* @ClassName: MainController 
* 菜单权限管理类,每个方法要判断用户是否有访问权限
*/
@Controller
@RequestMapping("/manager")
public class ManagerController {
	private Logger log = LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	private AdminService easyuitreeService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private AppEngineService appEngineService;
	@Autowired
	private LoginService loginSevice;
	
	@RequestMapping(value="/thrid",method=RequestMethod.GET)
	public String toIndex(Model model){
		return "userinfo";
	}
	
	/**
	 * 初始化应用引擎的数据模型定义
	 * @return
	 */
	@RequestMapping(value="/appEngine",method=RequestMethod.GET)
	public String toAppEngine(){
		return "appEngine";
	}
	
	@RequestMapping(value="/createEntityTable",method=RequestMethod.POST)
	@ResponseBody
	public String createEntityTable(@RequestParam(value="data")String data,@RequestParam(value="tableInfo")String tableInfo){
		JSONObject json = new JSONObject();
		try {
			this.easyuitreeService.createEntityTable(data,tableInfo);
			json.put("status", true);
			json.put("msg", "数据表操作成功!");
		} catch (Exception e) { //异常返回信息
			json.put("status", false);
			json.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return json.toString();
	}
	
	@RequestMapping(value="/createDataMoudle",method=RequestMethod.POST)
	@ResponseBody
	public String createDataMoudle(@RequestParam(value="data")String data,@RequestParam(value="tableInfo")String tableInfo){
		JSONObject json = new JSONObject();
		try {
			this.appEngineService.createDataMoudle(tableInfo, data);
			json.put("status", true);
			json.put("msg", "实体生成成功!");
		} catch (Exception e) { //异常返回信息
			json.put("status", false);
			json.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return json.toString();
	}
	
	/**
	 * 编辑菜单功能依赖数据表
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/editDataTable",method=RequestMethod.POST)
	@ResponseBody
	public String editDataTable(@RequestParam(value="data") String data){
		JSONObject json = new JSONObject();
		try {
			Submenu submenu = (Submenu)JSONObject.toBean(JSONObject.fromObject(data), Submenu.class);
			this.easyuitreeService.editDataTable(submenu);
			json.put("status", true);
			json.put("msg", "数据实体操作成功!");
		} catch (Exception e) { //捕获异常返回错误信息
			e.printStackTrace();
			json.put("status", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	
	/**
	 * 查询数据表结构
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryEntity",method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> queryEntity(@RequestParam(value="id")String id,@RequestParam(value="tableName")String tableName){
		if(!Tools.StringIsNullOrSpace(tableName)){
			List<Map<String, Object>> list = this.easyuitreeService.queryEntityByName(tableName);
			return list;
		}
		List<Map<String, Object>> list = this.easyuitreeService.queryEntityByid(id);
		if(list == null || list.size() ==0){ //数据为空
			list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "false");
			list.add(map);
			return list;
		}
		return list;
	}
	/**
	 * 查询树形菜单
	 * @return
	 */
	@RequestMapping(value="/queryMenu",method=RequestMethod.POST)
	public @ResponseBody List<JsonTreeData> queryMenu(){
		return this.easyuitreeService.queryMenuToList();
	}
	
	/**
	 * 用户管理界面初始化
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/usermanager",method=RequestMethod.GET)
	public String toUserManager(HttpSession session,HttpServletRequest request) throws Exception{
		Cookie[] cookies = request.getCookies();
		Map<String,String> cookieMap = new HashMap<String,String>();
		for (Cookie cookie : cookies) {
			cookieMap.put(cookie.getName(), cookie.getValue());
		}
		String id = new String(new BASE64Decoder().decodeBuffer(cookieMap.get("uso")),"UTF-8").split(":")[0];
		Admin admin = this.loginSevice.queryAdminByid(id);
		if("admin".equals(admin.getUsername()))
		{
			admin = null;
			return "userinfo";
		}else
		{
			log.error("你不是管理员,没有权限访问!");
			throw new Exception("你不是管理员,没有权限访问!");
		}
	}
	
	/**
	 * 新增、修改用户界面初始化
	 * @param _id
	 * @return
	 */
	@RequestMapping(value="/toAddModifyUse",method=RequestMethod.GET)
	public ModelAndView toEditUsePage(@RequestParam(value="_id") String _id){
		if(StringUtils.isEmpty(_id))//id为空直接跳转到界面
		{ 
			return new ModelAndView("editUse");
		}
		else
		{
			ModelAndView modelAdnView = new ModelAndView("editUse");
			modelAdnView.addObject("Admin", this.easyuitreeService.editUse(_id));
			return modelAdnView;
		}
	}
	
	/**
	 * 菜单管理界面初始化
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/menumanager",method=RequestMethod.GET)
	public String toMenumanager(HttpSession session,HttpServletRequest request) throws Exception{
		Cookie[] cookies = request.getCookies();
		Map<String,String> cookieMap = new HashMap<String,String>();
		for (Cookie cookie : cookies) {
			cookieMap.put(cookie.getName(), cookie.getValue());
		}
		String id = new String(new BASE64Decoder().decodeBuffer(cookieMap.get("uso")),"UTF-8").split(":")[0];
		Admin admin = this.loginSevice.queryAdminByid(id);
		if("admin".equals(admin.getUsername()))
		{
			admin = null;
			return "menuInfo";
		}else
		{
			log.error("你不是管理员,没有权限访问!");
			throw new Exception("你不是管理员,没有权限访问!");
		}
	}
	
	/**
	 * 查询用户管理数据
	 * @param page
	 * @param rows
	 * @param admin
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserManagerDatas",method=RequestMethod.POST)
	@ResponseBody
	public EasyuiResult getUserManagerData(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,@ModelAttribute Admin admin,HttpSession session,HttpServletRequest request) throws Exception{
		Cookie[] cookies = request.getCookies();
		Map<String,String> cookieMap = new HashMap<String,String>();
		for (Cookie cookie : cookies) {
			cookieMap.put(cookie.getName(), cookie.getValue());
		}
		String id = new String(new BASE64Decoder().decodeBuffer(cookieMap.get("uso")),"UTF-8").split(":")[0];
		Admin _admin = this.loginSevice.queryAdminByid(id);
		if("admin".equals(_admin.getUsername()))
		{
			_admin = null;
			return this.easyuitreeService.queryManagerAll(page, rows,admin);
		}else
		{
			throw new Exception("你不是管理员,没有权限访问!");
		}
	}
	
	/**
	 * 初始化菜单权限
	 * @param session
	 * @param _id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toMenuAuth",method=RequestMethod.GET)
	public ModelAndView toMenuAuthManager(HttpSession session,@RequestParam(value = "_id") String _id,HttpServletRequest request) throws Exception{
		Cookie[] cookies = request.getCookies();
		Map<String,String> cookieMap = new HashMap<String,String>();
		for (Cookie cookie : cookies) {
			cookieMap.put(cookie.getName(), cookie.getValue());
		}
		String id = new String(new BASE64Decoder().decodeBuffer(cookieMap.get("uso")),"UTF-8").split(":")[0];
		Admin _admin = this.loginSevice.queryAdminByid(id);
		if("admin".equals(_admin.getUsername()))
		{
			_admin = null;
			List<Menu> list = this.easyuitreeService.queryById(_id);
			return new ModelAndView("menuAuth").addObject("data",new Gson().toJson(list.get(1)))
					.addObject("_data", new Gson().toJson(list.get(0))).addObject("username", list.get(0).getUsername());
		}else
		{
			throw new Exception("你不是管理员,没有权限访问!");
		}
	}
	
	@RequestMapping(value="/saveAuth",method=RequestMethod.POST)
	@ResponseBody //自动转换为json
	public String updateAuthM(HttpSession session,@RequestParam(value = "_id") String _id,@RequestParam(value = "authmenu") String authmenu,@RequestParam(value = "secondMenu") String secondMenu) throws Exception{
		Admin _admin = this.loginService.queryAdminByid(_id);
		JSONObject json = new JSONObject();
		if("admin".equals(_admin.getUsername()))
		{
			_admin = null;
			int count = this.easyuitreeService.updateAuth(_id,authmenu,secondMenu);
			if(count > 0){
				json.put("status", true);
				json.put("msg", "权限更改成功!");
			}else{
				json.put("status", false);
				json.put("msg", "权限更改失败!");
			}
			return json.toString();
		}else{
			throw new Exception("你不是管理员,没有权限访问!");
		}
	}
}
