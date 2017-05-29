package com.example.web;
/**
 * 图片上传进度信息
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.UploadMsg;

public class UploadMsgServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            UploadMsg uplMsg=(UploadMsg) request.getSession().getAttribute("uplMsg");
            if(uplMsg!=null){
            	response.getWriter().write(uplMsg.toString());//应用UploadMsg中的toString()方法将数据转化为json格式字符串，写到页面上
            }
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
