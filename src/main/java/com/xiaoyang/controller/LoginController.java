package com.xiaoyang.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoyang.entity.Admin;
import com.xiaoyang.entity.Sign;
import com.xiaoyang.service.impl.LoginService;
import com.xiaoyang.util.ValidateCodeUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
* @ClassName: LoginController 
*/
@Controller
@RequestMapping
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoginService loginSevice;
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value="/index")
	public ModelAndView goMain(HttpSession session,HttpServletResponse response,HttpServletRequest request){
		Cookie[] cookies = null;
		Map<String,String> cookieMap = null;
		try {
			cookies =  request.getCookies();
			cookieMap = new HashMap<String,String>();
			if(cookies != null && cookies.length > 0  ){
				for (Cookie cookie : cookies) {
					cookieMap.put(cookie.getName(), cookie.getValue());
				}
			}else{
				response.sendRedirect("login.jsp");
				return null;
			}
			if(cookieMap.get("uso") != null &&  cookieMap.get("userinfo") != null && "ok".equals(cookieMap.get("userinfo"))){
				//把用户信息传给前台
				ModelAndView mav=new ModelAndView("well");
				//通过cookie查询用户信息
				String userinfo = cookieMap.get("uso");
				String id = new String(new BASE64Decoder().decodeBuffer(userinfo),"UTF-8").split(":")[0];
				Admin admin = this.loginSevice.queryAdminByid(id);
				mav.addObject("username", admin.getUsername());
				mav.addObject("authmenus", admin.getAuthmenu());
				mav.addObject("secondMenu", admin.getSecondMenu());
				Sign sign = this.loginSevice.querySign(admin.get_id());
				if(sign != null){
					mav.addObject("signin",sign.getSignin());
					mav.addObject("signout",sign.getSignout());
				}
				//mav.addObject("power", "0".equals(admin.getOrgid())?"管理员":"普通用户");
				return mav;
			}else{
				response.sendRedirect("login.jsp");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("登录异常!");
		}
		return null;
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getCode",method=RequestMethod.GET)
	public void getCode(HttpServletRequest request,HttpServletResponse response){
		try {
			response.setContentType("image/jpeg");
			//禁止图像缓存。  
		    response.setHeader("Pragma", "no-cache");  
		    response.setHeader("Cache-Control", "no-cache");  
		    response.setDateHeader("Expires", 0);
		    HttpSession session = request.getSession();  
		    
		    new ValidateCodeUtil(120,30,5,30).createCode();
		    session.setAttribute("code", ValidateCodeUtil.getCode());
		    ImageIO.write(ValidateCodeUtil.getBuffImg(), "png", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 验证用户登录信息
	 * @param _value
	 * @param session
	 * @return
	 */
	@RequestMapping(value="check",method=RequestMethod.POST)
	public String goCheck(@RequestParam(value = "_value")String _value,@RequestParam(value = "validation")String va,
			HttpSession session,HttpServletResponse response){
		try {
			boolean b = true; 
			if(!va.toUpperCase().equals(session.getAttribute("code"))){
				b = false;
				logger.info("登录失败,验证码错误!");
				return "redirect:/login.jsp";
			}
			String uso = this.loginSevice.query(_value);
			if(!"".equals(uso) && b){
			//	session.setAttribute("userinfo", b);
				Cookie cookie = new Cookie("userinfo", "ok");
				Cookie cookie2 = new Cookie("uso",new BASE64Encoder().encode(uso.getBytes("UTF-8"))); //设置cookie
				cookie.setPath("/");
				cookie.setMaxAge(3600);
				cookie2.setPath("/");
				cookie2.setMaxAge(3600);
				response.addCookie(cookie);
				response.addCookie(cookie2);
				return "redirect:index";
			}else{
				logger.info("登录失败,用户名或密码错误!");
				return "redirect:/login.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "redirect:/login.jsp";
	}
	
	/**
	 * 注销
	 * @param session
	 * @param response
	 */
	@RequestMapping("logout")
	public void goLogout(HttpSession session,HttpServletResponse response,HttpServletRequest request){
		Cookie[] Cookies = request.getCookies();
		if(Cookies != null && Cookies.length > 0){
			for (Cookie cookie : Cookies) {
				if("JSESSIONID".equals(cookie.getName()))continue;
				cookie.setValue(null);
				cookie.setMaxAge(1);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
//		session.setAttribute("userinfo", false);
//		session.setAttribute("user", null);
		session.setAttribute("code", null);
		try {
			response.sendRedirect("login.jsp");
		} catch (IOException e) {
			logger.error("注销异常!");
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/main",method=RequestMethod.GET)
	public String fff(){
		return "well";
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String queryall(){
		List<Admin> list = this.loginSevice.queryAll();
		for (Admin admin : list) {
			System.out.println(admin.getUsername());
			System.out.println(admin.getPassword());
		}
		return "ss";
	}
}
