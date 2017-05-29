package com.example.web;
/**
 * ��Ʒ���۰�����
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.SaleList;
import com.example.factory.BasicFactory;
import com.example.service.OrderService;

public class SaleListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    OrderService service=BasicFactory.getFactory().getService(OrderService.class);
            //1.����service��ȡ������Ʒ����Ϣ
		    List<SaleList> list=service.saleList();
		    //2.�����ݷ�װ��CSV��ʽ
		    StringBuffer buffer=new StringBuffer();
		    buffer.append("��Ʒ���,��Ʒ����,��������\r\t");//---"\r\t"��ʾ����
		    for(SaleList si:list)
		    {
		    	buffer.append(si.getProd_id()+","+si.getProd_name()+","+si.getSale_num()+"\r\t");
		    }
		   String data=buffer.toString();
		    //3.���س��۰�
		   String filename="Estore���۰�_"+new Date().toLocaleString()+".csv";
		   response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(filename,"UTF-8"));
		   response.setContentType(this.getServletContext().getMimeType(filename));
		   response.getWriter().write(data);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
