package com.example.filter;
/**
 * ���ù�������Cookieʵ���û���30���Զ���¼��
 */
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.User;
import com.example.factory.BasicFactory;
import com.example.service.UserService;

public class AutoLoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		//1.δ��¼���û��ſ����Զ���¼
		if(req.getSession(false)==null || req.getSession().getAttribute("user")==null)
		{
			Cookie [] cs=req.getCookies();
			Cookie findC=null;
			if(cs!=null)
			{
				for(Cookie c:cs)
				{
					if("autoLogin".equals(c.getName()))
					{
						findC=c;
						break;
					}
				}
			}
			if(findC!=null)
			{
				String str=findC.getValue();
			    str=URLDecoder.decode(str,"UTF-8");//��"UTF-8"�����ʽ���н���
				String username=str.split(":")[0];
				String password=str.split(":")[1];
				UserService service=BasicFactory.getFactory().getService(UserService.class);
				User user=service.getUserByNameAndPwd(username, password);
				if(user!=null)
				{
					req.getSession().setAttribute("user",user);
				}
				
			}
		}
		//2.������Ϊ"autoLogin"���Զ���¼Cookie�Ĳſ����Զ���¼
		//3.�Զ���¼Cookie�е��û���������ȫ����ȷ�����Զ���¼
		//4.����
		chain.doFilter(request, response);

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
