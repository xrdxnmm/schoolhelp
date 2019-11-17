package com.example.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.example.domain.User;
/**
 * 
 * @author XRDMM
 *
 */
public interface UserService {

	public int addUser(User user);
	
	public User login(User user) ;
	
	public User queryUser(String uName);
}
