package com.xiaoyang.util;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

/** 
* @ClassName: ExportExcelUtil 
* @Description: 导出excel抽象类
* @author yang
* @date 2016年12月9日 上午9:39:23 
*  
*/
public abstract class ExportExcelUtil {
	
	String fileName; //导出文件名
	
	public String getFileName() {
		return fileName;
	}

	/**
	 * 创建excel
	 * @return
	 */
	public HSSFWorkbook getWorkbook() throws Exception{
		List<?> data_list = this.getDataList();
		List<ExcelColumn> excelcolumn_list = this.getColumns();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		HSSFRow row = sheet.createRow(0);//创建列头
		
		CellStyle cellstyle;
		
		for (int i=0;i<excelcolumn_list.size();i++) { //遍历列头
			HSSFCell cell = row.createCell(i);
			cellstyle = workbook.createCellStyle();
			cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
			cell.setCellStyle(cellstyle);
			System.out.println(excelcolumn_list.get(i).getName());
			cell.setCellValue(new HSSFRichTextString(excelcolumn_list.get(i).getName()));
			if(excelcolumn_list.get(i).getWidth() != null){
				sheet.setColumnWidth(i, excelcolumn_list.get(i).getWidth());
			}
		}
		
		//假如数据为空，直接返回空
		if(data_list ==null || data_list.size() == 0) return workbook;
		
		for (int i = 0; i < data_list.size(); i++) {
			Object object = data_list.get(i);
			HSSFRow _row = sheet.createRow(i+1); //创建行
			for (int j = 0; j < excelcolumn_list.size(); j++) {
				HSSFCell cell = _row.createCell(j);
				cellstyle = workbook.createCellStyle();
				cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
				cell.setCellStyle(cellstyle);
				
				
				Method method = object.getClass().getMethod("get"+excelcolumn_list.get(j).getField().substring(0, 1).toUpperCase()+excelcolumn_list.get(j).getField().substring(1));
				Object objValue = method.invoke(object);
				
				if(objValue ==null ){
					cell.setCellValue(new HSSFRichTextString("")); //通过反射获取字段值
				}else{
					cell.setCellValue(new HSSFRichTextString(objValue.toString())); //通过反射获取字段值
				}
				
				if(excelcolumn_list.get(j).getWidth() != null){
					sheet.setColumnWidth(j, excelcolumn_list.get(j).getWidth());
				}
			}
		}
		
		return workbook;
	}
	
	/**
	 * 导出Excel文件名
	 * */
	public abstract String getExcelName();

	/**
	 * Excel数据源，调用服务返回list
	 * */
	public abstract List<?> getDataList();

	/**
	 * Excel列，及列属性
	 * */
	public abstract List<ExcelColumn> getColumns();
	
	/** 
	* @ClassName: ExcelColumn 
	* @Description: 内部构造类
	* @author yang
	* @date 2016年12月12日 上午9:51:02 
	*  
	*/
	protected class ExcelColumn{
		public ExcelColumn( String name, String field ) {
			this.name = name;
			this.field = field;
		}
		public ExcelColumn( String name, String field ,Integer width) {
			this.name = name;
			this.field = field;
			this.width = width;
		}
		private String name;
		private String field;
		private Integer width;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public Integer getWidth() {
			return width;
		}
		public void setWidth(Integer width) {
			this.width = width;
		}
	}
}
