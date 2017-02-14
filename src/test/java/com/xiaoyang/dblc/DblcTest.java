package com.xiaoyang.dblc;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiaoyang.entity.dblc.DBLC;
import com.xiaoyang.service.Impl.dblc.DBLCService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class DblcTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DBLCService dBLCService;
	
	public static void main(String[] args) {
		File file = new File("C:/Users/xiaoyang/Desktop/项目副本/NICEOA/src/main/webapp/upload/img/admin.jpg");
		try {
			FileInputStream in = new FileInputStream(file);
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			
			byte[] b = new byte[2048];
			int ch;
			while((ch=in.read(b)) != -1){
				bytes.write(b, 0, ch);
			}
			in.close();
			bytes.close();
			System.out.println("bytes.toByteArray():"+bytes.toByteArray().length);
			System.out.println("文件String："+bytes.toByteArray());
			System.out.println("长度为:"+new String(bytes.toByteArray(),"utf-8").length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void deleteDblcById(){
		List<DBLC> list = new ArrayList<DBLC>();
		DBLC dblc = new DBLC();
		dblc.setId(1);
		list.add(dblc);
		dblc = new DBLC();
		dblc.setId(2);
		list.add(dblc);
		int count = this.dBLCService.dBLC_delete(list);
		System.out.println("删除了数据："+count+"条");
	}
	
	@Test
	public void insertT(){
		try {
		File file = new File("C:/Users/xiaoyang/Desktop/项目副本/NICEOA/src/main/webapp/upload/img/admin.jpg");
		FileInputStream in = new FileInputStream(file);
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		
		byte[] b = new byte[2048];
		int ch;
		while((ch=in.read(b)) != -1){
			bytes.write(b, 0, ch);
		}
		in.close();
		bytes.close();
		this.jdbcTemplate.update("insert into DBLC(text,type,img) values(?,?,?)", new Object[]{"测试blob232","2",bytes.toByteArray()});
		}catch(Exception e){
			
		}
	}
	
	@Test
	public void insert(){
		List<DBLC> dblc_list = new ArrayList<DBLC>();
		DBLC dblc = new DBLC();
//		dblc.setText("测试数据");
//		dblc.setType(1);
//		dblc_list.add(dblc);
		
		dblc = new DBLC();
		dblc.setText("测试图片，byte方法");
		dblc.setType("2");
		
		File file = new File("C:/Users/xiaoyang/Desktop/项目副本/NICEOA/src/main/webapp/upload/img/admin.jpg");
		try {
			FileInputStream in = new FileInputStream(file);
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			byte[] b = new byte[2048];
			int c = 0 ;
			while((c = in.read(b)) != -1){
				bytes.write(b, 0, c);
			}
			in.close();
			bytes.close();
			dblc.setImg(bytes.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dblc_list.add(dblc);
		
		int count = this.dBLCService.dBLC_save(dblc_list);
		System.out.println(count);
		
	}
	
	@Test
	public void fdfd() throws Exception{
		File file = new File("C:/Users/xiaoyang/Desktop/项目副本/NICEOA/src/main/webapp/upload/img/admin.jpg");
		InputStream input = new FileInputStream(file);
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		
		byte[] bb = new byte[1024];
		int ch;
		while((ch = input.read(bb)) !=-1){
			bytes.write(bb, 0, ch);
		}
		
		byte[] ou =  bytes.toByteArray();
		System.out.println("ou:"+ou.length);
		
		readOut(ou);
		
		input.close();
		bytes.close();
	}
	
	@Test
	public void resdBlob(){
		try {
			Statement ps = this.jdbcTemplate.getDataSource().getConnection().createStatement();
			ResultSet rs = ps.executeQuery("select id,img from DBLC where id = 10");
			if(rs.next()){
				java.sql.Blob blob = (java.sql.Blob)rs.getBlob("img");
				InputStream input = blob.getBinaryStream(); //读取文件
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				
				byte[] bb = new byte[1024];
				int ch;
				while((ch = input.read(bb)) !=-1){
					bytes.write(bb, 0, ch);
				}
				
				byte[] ou =  bytes.toByteArray();
				System.out.println("ou:"+ou.length);
				
//				FileOutputStream out = new FileOutputStream(new File("d:/admin.jpg"));
//				
//				BufferedOutputStream bufferedOutput = new BufferedOutputStream(out);
//				
//				bufferedOutput.write(ou);
				
				readOut(ou);
				
				ps.close();
				rs.close();
				input.close();
				bytes.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void readOut(byte[] b){
		File file = new File("d:/admin.jpg");
		FileOutputStream out = null;
		BufferedOutputStream bufferedOutput = null; 
		try {
			out = new FileOutputStream(file);
			bufferedOutput = new BufferedOutputStream(out);
			bufferedOutput.write(b);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {  
            if (bufferedOutput != null) {  
                try {  
                	bufferedOutput.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (out != null) {  
                try {  
                	out.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
		 
	}
	
}
