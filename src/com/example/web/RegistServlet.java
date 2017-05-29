package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;

import com.example.domain.User;
import com.example.factory.BasicFactory;
import com.example.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service=BasicFactory.getFactory().getService(UserService.class);
        //1��У����֤���Ƿ���ȷ
		String valistr=request.getParameter("valistr");
		String valistr2=(String) request.getSession().getAttribute("valistr2");
		if(valistr==null || valistr2==null || valistr=="" || valistr2=="" || !valistr.equals(valistr2) )
		{
			request.setAttribute("msg","<font color='red'>��֤������</font>");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}else{
		//2����װ���ݺ�У�����ݣ�У�����Ҳ�����
			User user=new User();
		  try {
			BeanUtils.populate(user,request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//3������service�еķ�������ע��
		service.regist(user);
		//4���ض�����ҳ
		response.getWriter().write("��ϲ����ע��ɹ������ȵ�������м��");
		response.setHeader("Refresh","3;url=/Vesv/index.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doGet(request,response);
	}

}
