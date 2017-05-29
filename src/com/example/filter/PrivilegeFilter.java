package com.example.filter;
/**
 * �û�����Ȩ�޿���
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.User;

public class PrivilegeFilter implements Filter {
    private List<String> admin_list=new ArrayList<String>();
    private List<String> user_list=new ArrayList<String>();
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
         HttpServletRequest req=(HttpServletRequest) request;
         HttpServletResponse res=(HttpServletResponse) response;
         String uri=req.getRequestURI();
         if(admin_list.contains(uri) || user_list.contains(uri))
         {	         
        	         if(req.getSession(false)==null || req.getSession().getAttribute("user")==null)
			         {//δ��¼����Ҫ��ʾ�ȵ�¼
			        	  throw new RuntimeException("���ȵ�¼������");
			        	  
			         }else{
			        	 User user=(User) req.getSession().getAttribute("user");
			        	 //����ǹ���ԱȨ���Ҹ�URI�ڹ���Ա����Ȩ���ڣ�����п��Է���
			        	 if(admin_list.contains(uri) && "admin".equals(user.getRole()))
			        	 {
			        		 chain.doFilter(request, response);
			        	 }else if(user_list.contains(uri) && "user".equals(user.getRole()))
			        	 {//�������ͨ�û��Ҹ�URI����ͨ�û��ķ���Ȩ���ڣ�����п��Է���
			        		 chain.doFilter(request, response);
			        	 }else{//����֮�⣬˵������û����Ӧ��Ȩ�ޣ���ֹ���ʣ�
			        		 throw new RuntimeException("�Բ����������з��ʵ�Ȩ�ޣ�����");
			        	 }
			         }
         }else{//����ҪȨ�ޣ�URIû�з���Ȩ�����ã�
        	 chain.doFilter(request, response);
         }
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context=filterConfig.getServletContext(); 
		try {//��ȡadmin.txt�ļ���user.txt�ļ����ֱ��ȡ��admin_list��user_list��
			BufferedReader adminBuffer=new BufferedReader(new FileReader(context.getRealPath("WEB-INF/admin.txt")));
		    String line=null;
			while((line=adminBuffer.readLine())!=null)
			{
				admin_list.add(line);
			}
			
			BufferedReader userBuffer=new BufferedReader(new FileReader(context.getRealPath("WEB-INF/user.txt")));
		    line=null;
			while((line=userBuffer.readLine())!=null)
			{
				user_list.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
