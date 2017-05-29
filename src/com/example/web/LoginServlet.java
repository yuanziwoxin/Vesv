package com.example.web;
/**
 * �û���¼
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
        //1.��ȡ�û���������
    	String username=request.getParameter("username");
    	String password=MD5Utils.md5(request.getParameter("password"));
    	//2.����service�еķ����ж��û����������Ƿ�ƥ��
    	User user=service.getUserByNameAndPwd(username,password);
    	if(user==null)
    	{//����û��������벻ƥ�䣬����ʾ�û����������д�
    		request.setAttribute("msg","�û�������������");
    		request.getRequestDispatcher("/login.jsp").forward(request, response);
    	    return;//��ס����return���Է�ֹ��������������ת��Ҫ�ر��ס��һ�㣩
    	}
    	//3.�ж��û��Ƿ񼤻δ���������¼����ʾ�û�ȥ�����û�
    	if(user.getState()==0)
    	{
    		request.setAttribute("msg","�û�δ������ȵ�������м�����ٵ�¼��");
    		request.getRequestDispatcher("/login.jsp").forward(request, response);
    		return;//��ס����return���Է�ֹ��������
    	}
    	//4.�������¼�û�
    	
    	//����û�����������ȷ�����¼�û���
    	request.getSession().setAttribute("user", user);
    	  //----��1����ס�û���
   
    	//���
    	if("true".equals(request.getParameter("remname"))){
    		   //���ѡ�У����ס�û���
    		   Cookie remN=new Cookie("remName",URLEncoder.encode(user.getUsername(),encode));
    		   remN.setPath(request.getContextPath());
    		   remN.setMaxAge(3600*24*30);//����cookie30��
	       	   response.addCookie(remN);
    	}else{
    		   //δѡ�У��򲻱����û�����ɾ��cookie
    		   Cookie remN=new Cookie("remName","");
    		   remN.setPath(request.getContextPath());
    		   remN.setMaxAge(0);//����cookie30��
	       	   response.addCookie(remN);
       }
    	 //----��2��30���Զ���¼
    	if("true".equals(request.getParameter("autologin")))
    	{
    		Cookie autoL=new Cookie("autoLogin",URLEncoder.encode(user.getUsername()+":"+user.getPassword(),encode));//��UTF-8��ʽ���б���
    	    autoL.setPath(request.getContextPath());
    	    autoL.setMaxAge(3600*24*30);
    	    response.addCookie(autoL);
    	}
    	
    	//5.��ת����ҳ
    	response.getWriter().write("�û���¼�ɹ���������ת����ҳ��");
    	response.sendRedirect(request.getContextPath()+"/index.jsp");
    	}
    

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         doGet(request,response);
	}

}
