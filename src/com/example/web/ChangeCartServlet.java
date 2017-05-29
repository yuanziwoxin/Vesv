package com.example.web;
/**
 * �ı乺�ﳵ��ĳ��Ʒ������
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

public class ChangeCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service=BasicFactory.getFactory().getService(ProdService.class);
        //1.��ȡ��Ʒid
		String id=request.getParameter("id");
		//2.����service������ȡ��Ʒ��Ϣ
		Product prod=service.findProdById(id);
		//3.��cartmap�н���Ʒ���������иı�
		if(prod==null)
		{
			throw new RuntimeException("��Ʒ�����ڣ���");
		}else{
			Map<Product,Integer> cartmap=(Map<Product, Integer>) request.getSession().getAttribute("cartmap");
			cartmap.put(prod,Integer.parseInt(request.getParameter("buynum")));//�ı���Ʒ������
		}
		
		//4.���ص����ﳵҳ��
		response.sendRedirect(request.getContextPath()+"/cartList.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doGet(request,response);
	}

}
