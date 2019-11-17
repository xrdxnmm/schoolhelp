package com.example.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.User;
import com.example.service.UserService;
import com.example.utils.FormatConversion;
import com.example.utils.MyEncoderByMd5;
import com.example.utils.MySession;

import net.sf.json.JSONObject;


/**
 * 
 * @author XRDMM
 *
 */
@Controller
@RequestMapping("userController")
public class UserController {

	FormatConversion formatConversion=new FormatConversion();
	
	String name;
	String uName;
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "addUser",produces = {"application/json;charset=UTF-8"})
	public String addUser(User user){
		
		userService.addUser(user);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("message", "success");
		map.put("status", "200");
		System.out.println(map);
		System.out.println("注册信息："+user.getuName());	
		//map
		//System.out.println("111222");
		//String string=map.toString();
		//String jsonString = JSON.toJSONString(map);
		//JSONObject jsonObject=JSONObject.fromObject(map); 
		//String string=JSONObject.fromObject(map).toString();
		String string=formatConversion.getJsonMap(map);
		System.out.println("string1："+string);
		return string;
	}
/*	public ModelAndView addUser(User user){
		System.out.println("注册："+user.getuName()+" "+user.getuPassword());
		
		userService.addUser(user);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message","success");
		modelAndView.setView(new MappingJackson2JsonView());
		return modelAndView;
	}*/
	
	@ResponseBody
	@RequestMapping(value = "queryUser",produces = {"application/json;charset=UTF-8"})
	public User queryUser(String uName){
		System.out.println("用户名："+uName);
		User user=userService.queryUser(uName);
		System.out.println("用户信息："+user.getuName());
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value = "login",produces = {"application/json;charset=UTF-8"})
	public String login(HttpServletRequest request,User user){
		User use=userService.login(user);
		System.out.println("1登陆信息姓名："+use.getName()+" | "+use.getuName());
		
		//创建session
		HttpSession session=request.getSession();
		session.setAttribute("name", use.getName());
		session.setAttribute("uName", use.getuName());
		Map<String, Object> map=new HashMap<String, Object>();
		if (use==null) {
			//账号密码错误或不存在
			map.put("message", "false");
			map.put("status", "201");	

		}else {
			System.out.println("更新1");
			//账号密码正确
			map.put("message", "success");
			map.put("status", "200");
			map.put("uName", use.getuName());
			map.put("name", use.getName());
			name=use.getName();
			uName=use.getuName();
//			session.setAttribute("name", use.getName());
		}
		System.out.println(map);
		String string=formatConversion.getJsonMap(map);
		System.out.println("string1："+string);
		return string;
	} 
	
//	@ResponseBody
//	@RequestMapping("session")
//	public String session(HttpServletRequest request, HttpServletResponse response) {
//		
//		session.setAttribute("uName", uName);		
//		session.setAttribute("name",name );
//		System.out.println("session");
//		return "login";
//	}
	/*public ModelAndView login(User user) {	
		ModelAndView mv=new ModelAndView();
		if(userService.login(user)!=null){
            //登录成功，将user对象设置到HttpSession作用范围域中
			mv.addObject("message","success");
			mv.setView(new MappingJackson2JsonView());
			System.out.println("登陆成功");
		}
		else {
			System.out.println("登陆失败");			
		}			
		return mv;	
	}*/
}
