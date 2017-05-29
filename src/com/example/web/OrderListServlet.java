package com.example.web;
/**
 * ��ѯ�����б�
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.Orders;
import com.example.domain.OrdersListForm;
import com.example.domain.User;
import com.example.factory.BasicFactory;
import com.example.service.OrderService;

public class OrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            OrderService service=BasicFactory.getFactory().getService(OrderService.class);
		    //1.��ȡ�û�id
		     User user=(User) request.getSession().getAttribute("user");//��ȡ�ô���session���е�user����
		     int userId=user.getId();
		    //2.����service����ͨ��(userId)��ѯ������Ϣ
		    List<OrdersListForm> list=service.GetOrdersById(userId);
		    //3.����ѯ���Ķ�����Ϣ�浽request���У�ͨ������ת����orderList.jspҳ����ʾ��
	        request.setAttribute("list",list);
	        request.getRequestDispatcher("/orderList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
