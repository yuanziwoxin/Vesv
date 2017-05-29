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
           //1.��ȡ��Ʒid
		  String id=request.getParameter("id");
	       //2.����service�еķ�����ȡ��Ʒ��Ϣ
		  Product prod=service.findProdById(id);
		  //3.��cartmap�����Ʒ�����֮ǰû�и���Ʒ������Ӹ���Ʒ����������Ʒ������Ϊ1�����������ԭ�л����ϼ���1��
		  /*
		   * �ر�ע�⣺Ϊ��ֹcartmap�ظ����ͬһ����Ʒ��Σ�����Ʒ��������ӣ����ǵ�����ͬ��Ʒ������ʾ��
		   *               ����Ӧ��ͨ��id��hashֵ�����ж��Ƿ���ͬһ����Ʒ����Ϊhashֵ���Ǿ���Ψһ�ģ���Product.java��id����ȡhashֵ����
		   */
		  if(prod==null)
		  {
			  throw new RuntimeException("����Ʒ�Ѿ��������ˣ���");
		  }else{
			  Map<Product,Integer>cartmap=(Map<Product, Integer>) request.getSession().getAttribute("cartmap");
			  cartmap.put(prod,cartmap.containsKey(prod)?cartmap.get(prod)+1:1);//�����������ԭ�л����ϼ���1����֮������Ϊ1��
		  }
		   //4.�ض��򵽹��ﳵҳ��չʾ��Ʒ(cartList.jsp)
		  response.sendRedirect(request.getContextPath()+"/cartList.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
