package com.example.web;
/**
 * 商品出售榜单下载
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
            //1.调用service获取出售商品的信息
		    List<SaleList> list=service.saleList();
		    //2.将数据封装成CSV格式
		    StringBuffer buffer=new StringBuffer();
		    buffer.append("商品编号,商品名称,销售数量\r\t");//---"\r\t"表示换行
		    for(SaleList si:list)
		    {
		    	buffer.append(si.getProd_id()+","+si.getProd_name()+","+si.getSale_num()+"\r\t");
		    }
		   String data=buffer.toString();
		    //3.下载出售榜单
		   String filename="Estore销售榜单_"+new Date().toLocaleString()+".csv";
		   response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(filename,"UTF-8"));
		   response.setContentType(this.getServletContext().getMimeType(filename));
		   response.getWriter().write(data);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
