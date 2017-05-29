package com.example.web;

/**
 * ���ü����뼤���û���
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.User;
import com.example.factory.BasicFactory;
import com.example.service.UserService;

public class ActiveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service=BasicFactory.getFactory().getService(UserService.class);
      //1.��ȡ�����룻
		String activecode=request.getParameter("activecode");
		//2.����service�еķ������м��
		User user=service.active(activecode);
		//3.��¼�û�
		request.setAttribute("user",user);
		//4.��ת����ҳ��
		response.getWriter().write("��ϲ������ɹ���3����Զ�������ҳ�棡");
		response.setHeader("Refresh","3;url=/Vesv/index.jsp");
	}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           doGet(request,response);
	}

}
