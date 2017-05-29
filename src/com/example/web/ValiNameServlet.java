package com.example.web;
/**
 * 利用Ajax方式在前端不刷新验证用户名是否存在
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.factory.BasicFactory;
import com.example.service.UserService;

public class ValiNameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    UserService service=BasicFactory.getFactory().getService(UserService.class);
            //1.获取用户名
		    String username=request.getParameter("username");
		    //2.调用service方法验证用户名是否存在
		    String msg=null;
		    boolean b=service.VailName(username);
		    if(b){//利用json数据格式
		    	msg="{msg:'用户名已存在',state:1}";
		    }else{
		    	msg="{msg:'用户名可以使用',state:0}";
		    }
		    response.getWriter().write(msg);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
