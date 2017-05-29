package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.Product;
import com.example.factory.BasicFactory;
import com.example.service.ProdService;

public class AddCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  ProdService service=BasicFactory.getFactory().getService(ProdService.class);
           //1.获取商品id
		  String id=request.getParameter("id");
	       //2.调用service中的方法获取商品信息
		  Product prod=service.findProdById(id);
		  //3.向cartmap添加商品，如果之前没有该商品，则添加该商品，并设置商品的数量为1，如果有则在原有基础上加上1；
		  /*
		   * 特别注意：为防止cartmap重复添加同一件商品多次，而商品数量不相加，而是当做不同商品进行显示，
		   *               这里应该通过id的hash值进行判断是否是同一件商品，因为hash值才是绝对唯一的（在Product.java对id进行取hash值）；
		   */
		  if(prod==null)
		  {
			  throw new RuntimeException("该商品已经不存在了！！");
		  }else{
			  Map<Product,Integer>cartmap=(Map<Product, Integer>) request.getSession().getAttribute("cartmap");
			  cartmap.put(prod,cartmap.containsKey(prod)?cartmap.get(prod)+1:1);//如果存在则在原有基础上加上1，反之则设置为1；
		  }
		   //4.重定向到购物车页面展示商品(cartList.jsp)
		  response.sendRedirect(request.getContextPath()+"/cartList.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
