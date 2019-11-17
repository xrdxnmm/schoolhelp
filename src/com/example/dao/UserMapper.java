package com.example.dao;

import org.apache.ibatis.annotations.Param;

import com.example.domain.User;

/**
 * 
 * @author XRDMM
 *
 */
public interface UserMapper {

	/**
	 *	注册
	 */
	public int addUser(User user);
	
	/**
	 * 登陆
	 */
	public User login(User user);	
	/**
	 * 获取用户信息
	 * @param uName
	 * @return
	 */
	public User queryUser(String uName);
	
}
