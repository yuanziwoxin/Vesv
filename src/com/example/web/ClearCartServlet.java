package com.example.web;
/**
 * 清空购物车
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.Product;

public class ClearCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            //清空购物车（删除购物车中的所有商品）
		     Map<Product,Integer> cartmap=(Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		      cartmap.clear();//清除map中的数据，也即清空购物车
		     //重定向到购物车页面
		      response.sendRedirect(request.getContextPath()+"/cartList.jsp");
		     
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
