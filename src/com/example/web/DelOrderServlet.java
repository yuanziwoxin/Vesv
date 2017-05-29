package com.example.web;
/**
 * 删除订单
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
            //1.获取订单id
		     String id=request.getParameter("id");
		    //2.调用service的方法删除订单
		     service.DelOrderById(id);
		    //3.回到订单列表页面
		     response.getWriter().write("订单删除成功！3秒后将跳转到订单列表页面...");
		     response.setHeader("Refresh","3;url=/Estore/orderList.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
             doGet(request,response);
	}

}
