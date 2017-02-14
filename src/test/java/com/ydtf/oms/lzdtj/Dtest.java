package com.ydtf.oms.lzdtj;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "/com/ydtf/oms/lzdtj/lzdtj-sql.xml";
		InputStream input = Dtest.class.getResourceAsStream(fileName); 
		SAXReader saxReader = new SAXReader();  
		StringBuffer data = new StringBuffer();
		Date date = new Date();
		SimpleDateFormat sdf;
		try {
			Document document = saxReader.read(input);
			Element element = document.getRootElement();
			List<?> list = element.elements();
			for (Object object : list) {
				data.append("<数据体>");
				data.append("<业务ID>").append("121").append("</业务ID>");
				data.append("<局编码>").append("05").append("</局编码>");
				Element elementC = (Element) object;
				for (Iterator i$ = elementC.elementIterator();i$.hasNext();) {
					Element sqlC = (Element)i$.next();
					if("表单名".equals(sqlC.getName()))
					{
						data.append("<表单名称>").append(sqlC.getText()).append("</表单名称>");
					}
					else if("今日申请单数".equals(sqlC.getName()))
					{
						sdf = new SimpleDateFormat("yyyy-MM-dd");
						String day = sdf.format(date) + "%";
						String sql = sqlC.getText();
						sql = String.format(sql, day,day); //转换时间
						
						data.append("<今日申请单数>").append("").append("</今日申请单数>");
					}
					else if("今日流转中数".equals(sqlC.getName()))
					{
						sdf = new SimpleDateFormat("yyyy-MM-dd");
						String day = sdf.format(date) + "%";
						String sql = sqlC.getText();
						sql = String.format(sql, day,day); //转换时间
						
						data.append("<今日流转中数>").append(sqlC.getText()).append("</今日流转中数>");
					}
					else if("今日归档数".equals(sqlC.getName()))
					{
						sdf = new SimpleDateFormat("yyyy-MM-dd");
						String day = sdf.format(date) + "%";
						String sql = sqlC.getText();
						sql = String.format(sql, day,day); //转换时间
						
						data.append("<今日归档数>").append(sqlC.getText()).append("</今日归档数>");
					}
					else if("月累计启动数".equals(sqlC.getName()))
					{
						sdf = new SimpleDateFormat("yyyy-MM");
						String day = sdf.format(date) + "%";
						String sql = sqlC.getText();
						sql = String.format(sql, day,day); //转换时间
						
						data.append("<月累计启动数>").append(sqlC.getText()).append("</月累计启动数>");
					}
					else if("月累计归档数".equals(sqlC.getName()))
					{
						sdf = new SimpleDateFormat("yyyy-MM");
						String day = sdf.format(date) + "%";
						String sql = sqlC.getText();
						sql = String.format(sql, day,day); //转换时间
						
						data.append("<月累计归档数>").append(sqlC.getText()).append("</月累计归档数>");
					}
				}
				data.append("</数据体>");
			}
			System.out.println(data.toString());
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
