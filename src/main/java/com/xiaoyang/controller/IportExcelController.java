package com.xiaoyang.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoyang.entity.Admin;
import com.xiaoyang.service.impl.AdminService;


/** 
* @ClassName: IportExcelController 
* @Description: 下载模板，导入excel 
* @author yang
* @date 2016年12月13日 下午3:03:10 
*  
*/
@Controller
@RequestMapping("/import")
public class IportExcelController {
	@Autowired
	private AdminService adminService;
	/**
	 * 用excel导入用户信息
	 * produces定义响应的格式
	 * @param uploadFile
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/importExcel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> importExcel(@RequestParam("fileImport")MultipartFile uploadFile){
		Map<String,Object> map = new HashMap<String, Object>();
		Workbook workbook = null;
		try {
			if(StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), ".xls")){
				workbook = new HSSFWorkbook(uploadFile.getInputStream());
			}else if(StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), ".xlsx")){
				workbook = new XSSFWorkbook(uploadFile.getInputStream());
			}else{
				throw new Exception("excel的文件格式不正确!");
			}
			Sheet sheet = workbook.getSheetAt(0);
			//判断sheet是否为空
			
			//首先获取标题头
			String title = sheet.getRow(0).getCell(0).getStringCellValue();
			if(StringUtils.isEmpty(title) || !"用户信息".equals(title)){
				throw new Exception("excel格式错误,请下载模板导入!");
			}
			
			//获取导入的excel有几列
			int row = sheet.getLastRowNum();
			if(row == 1){ //假如只有两行，表示数据为空
				throw new Exception("excel为空,请重新导入!");
			}
			
			List<Admin> list_admin = new ArrayList<Admin>();
			for (int i = 1; i <= row; i++) {
				short col = sheet.getRow(i).getLastCellNum();
				boolean canSave = true;
				Admin admin = new Admin();
				for (int j = 0; j < col; j++) {
					String str = sheet.getRow(i).getCell(j).getStringCellValue();
//					System.out.println(str);
					if(i == 1){  //第二行，判断标题名称是否正确
						if(!checkFieldName(str,j)){ //判断标题名称是否正确
							throw new Exception("excel格式错误,请下载模板导入!");
						}
					}else{
						canSave = doFieldData(str,j,admin); //判断，并处理数据,返回为false，错误信息，存放在srt里
					}
				}
				if(canSave && i != 1){  //去掉第一个空行
					list_admin.add(admin);
				}
			}
			if(list_admin.size() > 0){ //保存数据到数据库
				this.adminService.insertAdmin(list_admin);
				map.put("success", true);
				map.put("msg", "数据保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			//处理异常
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	/**
	 * @param str
	 * @param j
	 * @param list_admin
	 */
	private boolean doFieldData(String str, int j,Admin admin) {
		boolean result = true;
		switch (j) {
		case 0: //用户编号
			if(StringUtils.isNotEmpty(str) && StringUtils.indexOf(str, "XZHR") != -1){
				admin.set_id(str);
			}else if(StringUtils.isNotEmpty(str) && StringUtils.indexOf(str, "XZHR") == -1){
				result = false;
				break;
			}else{ //为空,手动生成UUID
				admin.set_id("XZHR-"+UUID.randomUUID().toString().split("-")[0]);
			}
			break;
		case 1: //用户名
			if(StringUtils.isNotEmpty(str)){
				admin.setUsername(str);
			}else{
				break;
			}
			break;
		case 2: //创建时间
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			boolean canParse = true;
			try {
				format.setLenient(true);  
				format.parse(str); //间接通过格式转换，判断格式是否符合
			} catch (Exception e) {
				canParse = false;
			}
			if(StringUtils.isNotEmpty(str) && canParse){
				admin.setCreatetime(str);
			}else if(StringUtils.isNotEmpty(str) && !canParse){
				result = false;
				break;
			}else {
				admin.setCreatetime(format.format(new Date()));
			}	
			break;
		case 3: //用户姓名
			if(StringUtils.isNotEmpty(str)){
				admin.setName(str);
			}else{
				break;
			}
			break;
		case 4: //所属部门
			if(StringUtils.isNotEmpty(str) && "总经理办公室".equals(str))admin.setDept("1");
			else if(StringUtils.isNotEmpty(str) && "管理员".equals(str))admin.setDept("0");
			else if(StringUtils.isNotEmpty(str) && "人事部门".equals(str))admin.setDept("1_110");
			else if(StringUtils.isNotEmpty(str) && "技术部门".equals(str))admin.setDept("1_111");
			else if(StringUtils.isNotEmpty(str) && "行政部门".equals(str))admin.setDept("1_112");
			else if(StringUtils.isNotEmpty(str) && "业务部门".equals(str))admin.setDept("1_113");
			else if(StringUtils.isNotEmpty(str) && "财务部门".equals(str))admin.setDept("1_114");
			else if(StringUtils.isNotEmpty(str) && "采购部门".equals(str))admin.setDept("1_115");
			else if(StringUtils.isNotEmpty(str) && "后勤部门".equals(str))admin.setDept("1_116");
			else if(StringUtils.isEmpty(str)){result = false;break;}
			break;
		case 5: //上级部门
			if(StringUtils.isNotEmpty(str) && "总经理办公室".equals(str))admin.setOrganization("1");
			else if(StringUtils.isNotEmpty(str) && "管理员".equals(str))admin.setOrganization("0");
			else if(StringUtils.isNotEmpty(str) && "人事部门".equals(str))admin.setOrganization("1_110");
			else if(StringUtils.isNotEmpty(str) && "技术部门".equals(str))admin.setOrganization("1_111");
			else if(StringUtils.isNotEmpty(str) && "行政部门".equals(str))admin.setOrganization("1_112");
			else if(StringUtils.isNotEmpty(str) && "业务部门".equals(str))admin.setOrganization("1_113");
			else if(StringUtils.isNotEmpty(str) && "财务部门".equals(str))admin.setOrganization("1_114");
			else if(StringUtils.isNotEmpty(str) && "采购部门".equals(str))admin.setOrganization("1_115");
			else if(StringUtils.isNotEmpty(str) && "后勤部门".equals(str))admin.setOrganization("1_116");
			else if(StringUtils.isEmpty(str)){result = false;break;}
			break;	
		case 6:
			if(StringUtils.isNotEmpty(str)){
				admin.setJob(str);
			}else{
				result = false;
				break;
			}
			//设置初始化密码
			admin.setPassword("1q1q1q1q");
			break;
		default:
			break;
		}
		return result;
	}

	/**
	 * 判断标题名称是否正确
	 * @param str
	 * @param j
	 */
	private boolean checkFieldName(String str, int j) {
		boolean result = false;
		switch (j) {
		case 0:
			if(StringUtils.isNotEmpty(str) && "用户编号".equals(str)){
				result = true;
			}else{
				result = false;
			}
			break;
		case 1:
			if(StringUtils.isNotEmpty(str) && "用户名".equals(str)){
				result = true;
			}else{
				result = false;
			}
			break;
		case 2:
			if(StringUtils.isNotEmpty(str) && "创建时间".equals(str)){
				result = true;
			}else{
				result = false;
			}
			break;
		case 3:
			if(StringUtils.isNotEmpty(str) && "员工姓名".equals(str)){
				result = true;
			}else{
				result = false;
			}
			break;
		case 4:
			if(StringUtils.isNotEmpty(str) && "所属部门".equals(str)){
				result = true;
			}else{
				result = false;
			}
			break;
		case 5:
			if(StringUtils.isNotEmpty(str) && "上级部门".equals(str)){
				result = true;
			}else{
				result = false;
			}	
			break;
		case 6:
			if(StringUtils.isNotEmpty(str) && "职位".equals(str)){
				result = true;
			}else{
				result = false;
			}	
			break;	
		default:
			break;
		}
		return result;
	}

	/**
	 * 下载模板 
	 * @param filedName
	 */
	@RequestMapping("/downloadExcel")
	public void DownloadExcel(@RequestParam(value="fieldName")String[] filedName,@RequestParam(value="depts")String[] dept,
			@RequestParam(value="organizations")String[] organization,HttpServletResponse response){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet0");
		int rownum = 0;
		HSSFRow row = sheet.createRow(rownum);
		CellStyle cellStyle;
		
		//excel标题设置,合并
		HSSFCell cellTitle = row.createCell(0); //创建一列
		rownum ++;
		cellTitle.setCellValue("用户信息");
		HSSFFont font = workbook.createFont(); //创建字体
		font.setFontName("仿宋_GB2312"); //字体样式
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体加粗
		//font.setFontHeight((short)12); //高度设置为12
		font.setFontHeightInPoints((short)12);
		
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFont(font);
		cellTitle.setCellStyle(cellStyle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, filedName.length-2)); //合并标题居中
		
		row = sheet.createRow(rownum);
		for (int i = 0; i < filedName.length; i++) {
			if("菜单权限".equals(filedName[i]))continue;
			HSSFCell cell = row.createCell(i);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell.setCellStyle(cellStyle);
			if("".equals(filedName[i]))
				cell.setCellValue(new HSSFRichTextString("用户编号"));
			else
				cell.setCellValue(new HSSFRichTextString(filedName[i]));
			sheet.setColumnWidth(i, 5000);
		}
		rownum ++ ;
		//设置数据区域为10行,有效编辑为10行
		int datanum = 5;
		for(int i = 0;i < datanum;i++){
			//设置这10行数据样式
			HSSFRow datarow = sheet.createRow(rownum);
			for (int j = 0; j < filedName.length; j++) {
				if("菜单权限".equals(filedName[j]))continue;
				HSSFCell cell = datarow.createCell(j); //创建单元格
				HSSFDataFormat format =  workbook.createDataFormat();
				cellStyle = workbook.createCellStyle();
				if("".equals(filedName[j])){ //设置部门编号
					cell.setCellValue("XZHR-"+UUID.randomUUID().toString().split("-")[0]);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cellStyle.setDataFormat(format.getFormat("@"));
				}else if("创建时间".equals(filedName[j])){ //设置创建时间的样式
					cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					cellStyle.setDataFormat(format.getFormat("@"));
				}else if("所属部门".equals(filedName[j])){ 
					org.apache.poi.ss.util.CellRangeAddressList cellrangeaddresslist = new org.apache.poi.ss.util.CellRangeAddressList(cell.getRowIndex(), cell.getRowIndex(), cell.getColumnIndex(), cell.getColumnIndex());
					DVConstraint dvconstraint = DVConstraint.createExplicitListConstraint(dept);
					//数据有效性对象
					HSSFDataValidation datavalidation = new HSSFDataValidation(cellrangeaddresslist, dvconstraint);
					workbook.getSheetAt(0).addValidationData(datavalidation);
				}else if("上级部门".equals(filedName[j])){
					org.apache.poi.ss.util.CellRangeAddressList cellrangeaddresslist = new org.apache.poi.ss.util.CellRangeAddressList(cell.getRowIndex(), cell.getRowIndex(), cell.getColumnIndex(), cell.getColumnIndex());
					DVConstraint dvconstraint = DVConstraint.createExplicitListConstraint(organization);
					//数据有效性对象
					HSSFDataValidation datavalidation = new HSSFDataValidation(cellrangeaddresslist, dvconstraint);
					workbook.getSheetAt(0).addValidationData(datavalidation);
				}else{//定义为String类型
					cell.setCellValue("");
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cellStyle.setDataFormat(format.getFormat("@")); //设置为文本
				}
				cell.setCellStyle(cellStyle);
			}
			rownum ++ ;
		}
		
		try {
			String filename= java.net.URLEncoder.encode( "用户信息模板.xls", "utf-8" );
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
