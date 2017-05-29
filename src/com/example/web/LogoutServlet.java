package com.example.web;
/**
 * 注销登录
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          //1.判断session是否存在，且判断是否有登录用户，都存在则杀死；（false表示如果session之前没有创建就返回null，true表示如果没有创建则创建一个新的session）
		if(request.getSession(false)!=null && request.getSession().getAttribute("user")!=null)
		{
			request.getSession().invalidate();//杀死session
			
			//删除Cookie，不然用户选择自动登录后不能进行注销
			Cookie autoL=new Cookie("autoLogin","");
			autoL.setPath(request.getContextPath());
			autoL.setMaxAge(0);//设置生存周期为0秒，即相当于直接删除cookie；
			response.addCookie(autoL);
		}
		//2.重定向到主页
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           doGet(request,response);
	}

}
