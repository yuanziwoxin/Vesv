package com.example.web;
/**
 * 用户登录
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.User;
import com.example.factory.BasicFactory;
import com.example.service.UserService;
import com.example.util.MD5Utils;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	UserService service=BasicFactory.getFactory().getService(UserService.class);
    	String encode=this.getServletContext().getInitParameter("encode");
        //1.获取用户名和密码
    	String username=request.getParameter("username");
    	String password=MD5Utils.md5(request.getParameter("password"));
    	//2.调用service中的方法判断用户名和密码是否匹配
    	User user=service.getUserByNameAndPwd(username,password);
    	if(user==null)
    	{//如果用户名和密码不匹配，则提示用户名或密码有错；
    		request.setAttribute("msg","用户名或密码有误！");
    		request.getRequestDispatcher("/login.jsp").forward(request, response);
    	    return;//记住加上return，以防止后续出错！（请求转发要特别记住这一点）
    	}
    	//3.判断用户是否激活，未激活则不予登录，提示用户去激活用户
    	if(user.getState()==0)
    	{
    		request.setAttribute("msg","用户未激活！请先到邮箱进行激活后再登录！");
    		request.getRequestDispatcher("/login.jsp").forward(request, response);
    		return;//记住加上return，以防止后续出错！
    	}
    	//4.激活则登录用户
    	
    	//如果用户名和密码正确，则登录用户；
    	request.getSession().setAttribute("user", user);
    	  //----（1）记住用户名
   
    	//如果
    	if("true".equals(request.getParameter("remname"))){
    		   //如果选中，则记住用户名
    		   Cookie remN=new Cookie("remName",URLEncoder.encode(user.getUsername(),encode));
    		   remN.setPath(request.getContextPath());
    		   remN.setMaxAge(3600*24*30);//保存cookie30天
	       	   response.addCookie(remN);
    	}else{
    		   //未选中，则不保存用户名，删除cookie
    		   Cookie remN=new Cookie("remName","");
    		   remN.setPath(request.getContextPath());
    		   remN.setMaxAge(0);//保存cookie30天
	       	   response.addCookie(remN);
       }
    	 //----（2）30天自动登录
    	if("true".equals(request.getParameter("autologin")))
    	{
    		Cookie autoL=new Cookie("autoLogin",URLEncoder.encode(user.getUsername()+":"+user.getPassword(),encode));//以UTF-8格式进行保存
    	    autoL.setPath(request.getContextPath());
    	    autoL.setMaxAge(3600*24*30);
    	    response.addCookie(autoL);
    	}
    	
    	//5.跳转到主页
    	response.getWriter().write("用户登录成功！即将跳转到主页！");
    	response.sendRedirect(request.getContextPath()+"/index.jsp");
    	}
    

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         doGet(request,response);
	}

}
