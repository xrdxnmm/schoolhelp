package com.example.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserMapper;
import com.example.domain.User;
import com.example.service.UserService;
import com.example.utils.MyEncoderByMd5;
/**
 * 
 * @author XRDMM
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	MyEncoderByMd5 md5=new MyEncoderByMd5();
	String uName;
	String uPassword;
	//@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 
	//通过 @Autowired的使用来消除 set ，get方法。
	//对一个bean配置起属性时，是用
	//<property name="属性名" value=" 属性值"/>  
	
	@Autowired
	UserMapper userMapper;
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public int addUser(User user) {
		
		System.out.println("加密\n");
		MyEncoderByMd5 md5=new MyEncoderByMd5();				
		System.out.println("\n加密前用户信息："+user.getuName()+" | "+user.getuPassword());			
		//String uName=user.getuName();
		String uPassword=user.getuPassword();
		//user.setuName(md5.EncoderByMd5(uName));
		user.setuPassword(md5.EncoderByMd5(uPassword));
		System.out.println("\n加密后用户信息："+user.getuName()+" | "+user.getuPassword());
		
		
		int result=userMapper.addUser(user);
		System.out.println("Seriver:注册:"+result);
		return result;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public User queryUser(String uName) {

		//String str=md5.EncoderByMd5(uName);
		User user=userMapper.queryUser(uName);
//		if(user!=null)
//			user.setuName(uName);
		System.out.println("用户信息："+user.getuName()+" | "+user.getName());
		return user;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public User login(User user){
		
		System.out.println("加密\n");				
		System.out.println("\n加密前用户信息："+user.getuName()+" | "+user.getuPassword());			
		//uName=user.getuName();
		uPassword=user.getuPassword();
		//user.setuName(md5.EncoderByMd5(uName));
		user.setuPassword(md5.EncoderByMd5(uPassword));
		System.out.println("\n加密后用户信息："+user.getuName()+" | "+user.getuPassword());
		System.out.println("更新");
		User use=userMapper.login(user);
		System.out.println("Seriver账号："+use.getName()+" | "+use.getuName());
		return use;
	}


	

}
