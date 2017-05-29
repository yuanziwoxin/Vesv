package com.example.web;
/**
 * ����Ajax��ʽ��ǰ�˲�ˢ����֤�û����Ƿ����
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
            //1.��ȡ�û���
		    String username=request.getParameter("username");
		    //2.����service������֤�û����Ƿ����
		    String msg=null;
		    boolean b=service.VailName(username);
		    if(b){//����json���ݸ�ʽ
		    	msg="{msg:'�û����Ѵ���',state:1}";
		    }else{
		    	msg="{msg:'�û�������ʹ��',state:0}";
		    }
		    response.getWriter().write(msg);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
