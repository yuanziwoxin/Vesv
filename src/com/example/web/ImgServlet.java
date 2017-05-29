package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImgServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            //获取imgurl参数(从GET方式提交的地址栏获取请求参数imgurl)
		     String imgurl=request.getParameter("imgurl");
		     request.getRequestDispatcher(imgurl).forward(request, response);//直接将图片地址请求转发过去
		     
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
