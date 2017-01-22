package com.xiaoyang.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.xiaoyang.entity.system.JsonTreeData;
import com.xiaoyang.service.impl.system.AdminService;

/** 
* @ClassName: TreeController 
*/
@Controller
@RequestMapping
public class TreeController {

	@Autowired
	private AdminService easyuitreeService;
	
	@RequestMapping(value="/tree",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getTree(Model model){
		List<JsonTreeData> list = this.easyuitreeService.queryAll();
		if(list.size() !=0 ){
			Gson gson = new Gson();
			String json =  gson.toJson(list); 
			return json;
		}
		return null;
	}
}
