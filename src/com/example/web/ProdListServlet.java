package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.Product;
import com.example.factory.BasicFactory;
import com.example.service.ProdService;

public class ProdListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   ProdService service=BasicFactory.getFactory().getService(ProdService.class);
            //1.调用service方法获取所有商品信息
		    List<Product> list=service.findProd();
		   //2.将商品信息放到request域中，然后请求转发到ProdList.jsp页面进行展示
	        request.setAttribute("list",list);
	        request.getRequestDispatcher("/prodList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           doGet(request,response);
	}

}
