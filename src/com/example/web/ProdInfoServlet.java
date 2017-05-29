package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.Product;
import com.example.factory.BasicFactory;
import com.example.service.ProdService;

public class ProdInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service=BasicFactory.getFactory().getService(ProdService.class);
           //1.��ȡ��Ʒ��id
		String id=request.getParameter("id");
		  //2.����service�еķ�����ȡ����Ʒ��Ϣ
		Product prod=service.findProdById(id);
		 //3.����request���У�����ת����prodInfo.jsp������ʾ
		if(prod==null)
		{
			throw new RuntimeException("�Ҳ�������Ʒ������");
		}else{
			request.setAttribute("prod",prod);
			request.getRequestDispatcher("/prodInfo.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
