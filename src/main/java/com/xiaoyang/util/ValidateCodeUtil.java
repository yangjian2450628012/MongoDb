package com.xiaoyang.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

/** 
* @ClassName: ValidateCodeUtil 
* @Description: 验证码生成类
* @author yang
* @date 2016年11月30日 上午11:11:57 
*/
public class ValidateCodeUtil {
	private static int width = 160; 
	private static int height = 40; 
	private static int codeCount = 5; 
	private static int lineCont = 120; 
	private static String code; 
	private static BufferedImage buffImg;
	private static char[] codespace = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',  
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	/**
	 * 使用默认参数
	 */
	public ValidateCodeUtil(){
		
	}
	
	/**
	 * 自定义宽、高
	 * @param width
	 * @param height
	 */
	public ValidateCodeUtil(int width, int height){
		ValidateCodeUtil.width = width;
		ValidateCodeUtil.height = height;
	}
	
	/**
	 * 自定义宽、高、验证码个数、线条个数
	 * @param width
	 * @param height
	 * @param codeCount
	 * @param lineCont
	 */
	public ValidateCodeUtil(int width, int height, int codeCount, int lineCont) {
		ValidateCodeUtil.width = width;
		ValidateCodeUtil.height = height;
		ValidateCodeUtil.codeCount = codeCount;
		ValidateCodeUtil.lineCont = lineCont;
	}
	
	public void createCode(){
		int x=0,fontHeight=0,codeY=0;
		
		x = width / (codeCount + 2); //每个字符的宽度(左右各空出一个字符) 
		fontHeight = height - 2; //字体的height
		codeY = height - 4;
		buffImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); //图像buffer
		
		Graphics2D graphics2d = buffImg.createGraphics();
		graphics2d.setColor(Color.white); //将图像填充为白色
		graphics2d.fillRect(0, 0, width, height);
		graphics2d.setFont(new Font("Fixedsys", Font.PLAIN, fontHeight));
		
		Random random = new Random(); //随机数
		for (int i = 0; i < lineCont; i++) {  //设置线条颜色、位置
			graphics2d.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			graphics2d.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width)+random.nextInt(width)/8, random.nextInt(height)+random.nextInt(height)/8);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < codeCount; i++) {
			String codeS = String.valueOf(codespace[random.nextInt(codespace.length)]);
			graphics2d.setColor(new Color(random.nextInt(100), random.nextInt(100), random.nextInt(100)));
			graphics2d.drawString(codeS, (i+1)*x, codeY);
			sb.append(codeS);
		}
		code = sb.toString(); //得到验证码,后期保存到session中，便于验证
	}

	/**
	 * 写入到磁盘中
	 * @param path
	 * @throws IOException
	 */
	public static void write(String path) throws IOException{
		OutputStream out = new FileOutputStream(path);
		ImageIO.write(buffImg, "png", out);
		out.close();
	}
	
	public static String getCode() {
		return code;
	}

	public static BufferedImage getBuffImg() {
		return buffImg;
	}

	public static void main(String[] args) {
		new ValidateCodeUtil().createCode();
		System.out.println("验证码为:"+ValidateCodeUtil.getCode());
		//写入到磁盘
		try {
			ValidateCodeUtil.write("D:/code/"+new Date().getTime()+".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
