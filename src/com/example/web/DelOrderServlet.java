package com.example.web;
/**
 * ɾ������
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.factory.BasicFactory;
import com.example.service.OrderService;

public class DelOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    OrderService service=BasicFactory.getFactory().getService(OrderService.class);
            //1.��ȡ����id
		     String id=request.getParameter("id");
		    //2.����service�ķ���ɾ������
		     service.DelOrderById(id);
		    //3.�ص������б�ҳ��
		     response.getWriter().write("����ɾ���ɹ���3�����ת�������б�ҳ��...");
		     response.setHeader("Refresh","3;url=/Estore/orderList.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
             doGet(request,response);
	}

}
