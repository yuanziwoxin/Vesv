package com.example.filter;
/**
 * 用户访问权限控制
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
			         {//未登录者需要提示先登录
			        	  throw new RuntimeException("请先登录！！！");
			        	  
			         }else{
			        	 User user=(User) req.getSession().getAttribute("user");
			        	 //如果是管理员权限且该URI在管理员访问权限内，则放行可以访问
			        	 if(admin_list.contains(uri) && "admin".equals(user.getRole()))
			        	 {
			        		 chain.doFilter(request, response);
			        	 }else if(user_list.contains(uri) && "user".equals(user.getRole()))
			        	 {//如果是普通用户且该URI在普通用户的访问权限内，则放行可以访问
			        		 chain.doFilter(request, response);
			        	 }else{//除此之外，说明访问没有相应的权限，阻止访问；
			        		 throw new RuntimeException("对不起！您不具有访问的权限！！！");
			        	 }
			         }
         }else{//不需要权限（URI没有访问权限设置）
        	 chain.doFilter(request, response);
         }
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context=filterConfig.getServletContext(); 
		try {//读取admin.txt文件和user.txt文件并分别存取到admin_list和user_list中
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
