package com.example.web;
/**
 * 查询订单列表
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
		    //1.获取用户id
		     User user=(User) request.getSession().getAttribute("user");//先取得存在session域中的user对象
		     int userId=user.getId();
		    //2.调用service方法通过(userId)查询订单信息
		    List<OrdersListForm> list=service.GetOrdersById(userId);
		    //3.将查询到的订单信息存到request域中，通过请求转发到orderList.jsp页面显示；
	        request.setAttribute("list",list);
	        request.getRequestDispatcher("/orderList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
