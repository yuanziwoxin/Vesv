package com.example.web;
/**
 * ��չ��ﳵ
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
            //��չ��ﳵ��ɾ�����ﳵ�е�������Ʒ��
		     Map<Product,Integer> cartmap=(Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		      cartmap.clear();//���map�е����ݣ�Ҳ����չ��ﳵ
		     //�ض��򵽹��ﳵҳ��
		      response.sendRedirect(request.getContextPath()+"/cartList.jsp");
		     
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
