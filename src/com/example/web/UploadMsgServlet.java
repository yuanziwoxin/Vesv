package com.example.web;
/**
 * ͼƬ�ϴ�������Ϣ
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
            	response.getWriter().write(uplMsg.toString());//Ӧ��UploadMsg�е�toString()����������ת��Ϊjson��ʽ�ַ�����д��ҳ����
            }
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
