package com.example.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MySession extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response,String uName)
			throws ServletException,IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session=request.getSession();
		session.setAttribute("uName", uName);
		String sessionID=session.getId();
		if(session.isNew()) {
			response.getWriter().print("session创建成功，id为："+sessionID);
			
		}else {
			response.getWriter().print("session已存在，id为："+sessionID);
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		doGet(request, response);
	}
}
