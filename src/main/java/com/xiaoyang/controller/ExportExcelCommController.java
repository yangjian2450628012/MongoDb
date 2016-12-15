package com.xiaoyang.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaoyang.entity.Admin;
import com.xiaoyang.service.impl.AdminService;
import com.xiaoyang.util.EasyuiResult;

@Controller
@RequestMapping("/export")
public class ExportExcelCommController {
	@Autowired
	private AdminService easyuitreeservice;
	
	@RequestMapping(value="/exportExcelUse",method=RequestMethod.POST)
	public void exportExcelUse(@ModelAttribute Admin admin,@RequestParam(value="fieldName")String[] cellName,@RequestParam(value="field")String[] field, HttpServletResponse response){
		try {
			//通过反射调用查询
			EasyuiResult result = this.easyuitreeservice.queryManagerAll(0, 0, admin); //默认查询出复合条件的所有数据
			@SuppressWarnings("unchecked")
			List<Admin> list = (List<Admin>)result.getRows();
			CellStyle cellStyle;
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			HSSFRow row = sheet.createRow(0);//创建一行:列头
			
			/*String[] cellName = new String[]{"用户名","创建时间","员工姓名","所属部门","上级部门","职位"};
			String[] field = new String[]{"username","createtime","name","dept","organization","job"};*/
			
			for (int i = 0; i < cellName.length; i++) { //给列头添加名称
				if("菜单权限".equals(cellName[i])){
					continue;
				}
				HSSFCell cell = row.createCell(i); //列
				cellStyle = workbook.createCellStyle(); //创建样式
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				
				cell.setCellStyle(cellStyle);
				if("".equals(cellName[i]))
					cell.setCellValue(new HSSFRichTextString("用户编号"));
				else
					cell.setCellValue(new HSSFRichTextString(cellName[i]));
				sheet.setColumnWidth(i, 5000);
				
			}
			
			for (int i = 0; i < list.size(); i++) {
				Admin _admin = list.get(i);
				HSSFRow _row = sheet.createRow(i+1); //从第一行开始，新添加一行
				if("1".equals(_admin.getDept())) _admin.setDept("总经理办公室");
				else if("0".equals(_admin.getDept()))_admin.setDept("管理员");
				else if("1_110".equals(_admin.getDept()))_admin.setDept("人事部门");
				else if("1_111".equals(_admin.getDept())) _admin.setDept("技术部门");
				else if("1_112".equals(_admin.getDept()))_admin.setDept( "行政部门");
				else if("1_113".equals(_admin.getDept()))_admin.setDept( "业务部门");
				else if("1_114".equals(_admin.getDept()))_admin.setDept( "财务部门");
				else if("1_115".equals(_admin.getDept()))_admin.setDept( "采购部门");
				else if("1_116".equals(_admin.getDept()))_admin.setDept( "后勤部门");
				
				if("1".equals(_admin.getOrganization())) _admin.setOrganization("总经理办公室");
				else if("0".equals(_admin.getOrganization()))_admin.setOrganization("管理员");
				else if("1_110".equals(_admin.getOrganization()))_admin.setOrganization("人事部门");
				else if("1_111".equals(_admin.getOrganization())) _admin.setOrganization("技术部门");
				else if("1_112".equals(_admin.getOrganization()))_admin.setOrganization( "行政部门");
				else if("1_113".equals(_admin.getOrganization()))_admin.setOrganization( "业务部门");
				else if("1_114".equals(_admin.getOrganization()))_admin.setOrganization( "财务部门");
				else if("1_115".equals(_admin.getOrganization()))_admin.setOrganization( "采购部门");
				else if("1_116".equals(_admin.getOrganization()))_admin.setOrganization( "后勤部门");
				
				for (int j = 0; j < field.length; j++) {
					if("auth".equals(field[j])){
						continue;
					}
					HSSFCell cell = _row.createCell(j);
					cellStyle = workbook.createCellStyle(); //创建样式
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					
					cell.setCellStyle(cellStyle);
					//通过反射获取Admin里面的字段值
					Method method = _admin.getClass().getMethod("get"+field[j].substring(0, 1).toUpperCase()+field[j].substring(1));
					Object obj = method.invoke(_admin);
					
					cell.setCellValue(new HSSFRichTextString(obj.toString())); //赋值
					
					sheet.setColumnWidth(i, 5000);
				}
				
			}
			String filename= java.net.URLEncoder.encode( "用户信息.xls", "utf-8" );
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="+filename);
			ServletOutputStream output = response.getOutputStream();
			workbook.write(output);
			output.flush();
			output.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
