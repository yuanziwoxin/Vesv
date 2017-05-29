package com.example.web;

/**
 * 利用激活码激活用户；
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
      //1.获取激活码；
		String activecode=request.getParameter("activecode");
		//2.调用service中的方法进行激活；
		User user=service.active(activecode);
		//3.登录用户
		request.setAttribute("user",user);
		//4.跳转到主页面
		response.getWriter().write("恭喜您激活成功！3秒后将自动跳到主页面！");
		response.setHeader("Refresh","3;url=/Vesv/index.jsp");
	}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           doGet(request,response);
	}

}
