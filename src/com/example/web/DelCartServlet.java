package com.example.web;
/**
 * 删除购物车中的商品
 */
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

public class DelCartServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service=BasicFactory.getFactory().getService(ProdService.class);
        //1.获取商品id
		String id=request.getParameter("id");
		//2.调用service方法获取商品信息
		Product prod=service.findProdById(id);
		//3.从cartmap中将商品从购物车删除
		if(prod==null)
		{
			throw new RuntimeException("商品不存在！！");
		}else{
			Map<Product,Integer> cartmap=(Map<Product, Integer>) request.getSession().getAttribute("cartmap");
			cartmap.remove(prod);//删除商品
		}
		
		//4.返回到购物车页面
		response.sendRedirect(request.getContextPath()+"/cartList.jsp");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
