package com.xiaoyang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.xiaoyang.entity.Admin;
import com.xiaoyang.entity.JsonTreeData;
import com.xiaoyang.entity.Menu;
import com.xiaoyang.service.impl.AdminService;
import com.xiaoyang.util.EasyuiResult;

import net.sf.json.JSONObject;

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
	
	@RequestMapping(value="/thrid",method=RequestMethod.GET)
	public String toIndex(Model model){
		return "userinfo";
	}
	
	@RequestMapping(value="/appEngine",method=RequestMethod.GET)
	public String toAppEngine(){
		return "appEngine";
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
	public String toUserManager(HttpSession session) throws Exception{
		Admin admin = (Admin)session.getAttribute("user");
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
	public String toMenumanager(HttpSession session) throws Exception{
		Admin admin = (Admin)session.getAttribute("user");
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
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,@ModelAttribute Admin admin,HttpSession session) throws Exception{
		Admin _admin = (Admin)session.getAttribute("user");
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
	public ModelAndView toMenuAuthManager(HttpSession session,@RequestParam(value = "_id") String _id) throws Exception{
		Admin _admin = (Admin)session.getAttribute("user");
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
	public Map<String,Object> updateAuthM(HttpSession session,@RequestParam(value = "_id") String _id,@RequestParam(value = "authmenu") String authmenu,@RequestParam(value = "secondMenu") String secondMenu) throws Exception{
		Admin _admin = (Admin)session.getAttribute("user");
		if("admin".equals(_admin.getUsername()))
		{
			_admin = null;
			int count = this.easyuitreeService.updateAuth(_id,authmenu,secondMenu);
			Map<String,Object> map = new HashMap<String, Object>();
			if(count > 0){
				map.put("status", true);
				map.put("msg", "权限更改成功!");
			}else{
				map.put("status", false);
				map.put("msg", "权限更改失败!");
			}
			return map;
		}else{
			throw new Exception("你不是管理员,没有权限访问!");
		}
	}
}
