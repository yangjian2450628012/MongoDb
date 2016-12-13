package com.xiaoyang.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoyang.entity.Admin;
import com.xiaoyang.service.impl.EasyuitreeService;
import com.xiaoyang.util.ExportExcelUtil;

/** 
* @ClassName: ExportExcel2Controller 
* @Description: 导出excel实现类
* @author yang
* @date 2016年12月12日 上午10:44:09 
*  
*/
@Controller
@RequestMapping("/excel")
public class ExportExcel2Controller extends ExportExcelUtil{
	@Autowired
	private EasyuitreeService easyuitreeservice;
	private Admin admin;
	
	@Override
	public String getExcelName() {
		return "用户信息.xls";
	}

	@Override
	public List<?> getDataList() {
		List<?> list = this.easyuitreeservice.queryManagerAll(0, 0, admin).getRows();
		return list;
	}

	/**
	 * 列名称和列字段名
	 */
	@Override
	public List<ExcelColumn> getColumns() {
		List<ExcelColumn> listcolumn = new ArrayList<ExcelColumn>();
		listcolumn.add(new ExcelColumn("用户名","username",8000));
		listcolumn.add(new ExcelColumn("创建时间","createtime",8000));
		listcolumn.add(new ExcelColumn("员工姓名","name",8000));
		listcolumn.add(new ExcelColumn("所属部门","dept",8000));
		listcolumn.add(new ExcelColumn("上级部门","organization",8000));
		listcolumn.add(new ExcelColumn("职位","job",8000));
		return listcolumn;
	}
	
	@RequestMapping("/getExcel")
	public void getOutput(@ModelAttribute Admin admin,HttpServletResponse response){
		this.admin = admin;
		String filename;
		try {
			filename = java.net.URLEncoder.encode( this.getExcelName(), "utf-8" );
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="+filename);
			ServletOutputStream out = response.getOutputStream();
			this.getWorkbook().write(out); //得到流，返回响应
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

	
