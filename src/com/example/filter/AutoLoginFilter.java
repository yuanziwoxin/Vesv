package com.example.filter;
/**
 * 利用过滤器和Cookie实现用户的30天自动登录；
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
		//1.未登录的用户才可以自动登录
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
			    str=URLDecoder.decode(str,"UTF-8");//以"UTF-8"编码格式进行解码
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
		//2.带有名为"autoLogin"的自动登录Cookie的才可以自动登录
		//3.自动登录Cookie中的用户名和密码全都正确才能自动登录
		//4.放行
		chain.doFilter(request, response);

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
