package com.example.web;
/**
 * ע����¼
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
          //1.�ж�session�Ƿ���ڣ����ж��Ƿ��е�¼�û�����������ɱ������false��ʾ���session֮ǰû�д����ͷ���null��true��ʾ���û�д����򴴽�һ���µ�session��
		if(request.getSession(false)!=null && request.getSession().getAttribute("user")!=null)
		{
			request.getSession().invalidate();//ɱ��session
			
			//ɾ��Cookie����Ȼ�û�ѡ���Զ���¼���ܽ���ע��
			Cookie autoL=new Cookie("autoLogin","");
			autoL.setPath(request.getContextPath());
			autoL.setMaxAge(0);//������������Ϊ0�룬���൱��ֱ��ɾ��cookie��
			response.addCookie(autoL);
		}
		//2.�ض�����ҳ
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           doGet(request,response);
	}

}
