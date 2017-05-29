package com.example.filter;
/**
 * 解决全站乱码问题
 *   装饰设计模式，内部类，Map<T,V>
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeFilter implements Filter{
    private static FilterConfig config=null;
    private static ServletContext context=null;
    private static String encode=null;
    private static boolean isNotEncode=true;
	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//解决response响应的乱码问题
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset="+encode);
		//利用装饰设计模式改变request对象和获取请求参数相关的方法，从而解决request的POST和GET方式的乱码问题；
		 chain.doFilter(new MyHttpServletRequest((HttpServletRequest) request), response);//放行
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config=filterConfig;
		this.context=filterConfig.getServletContext();
		this.encode=filterConfig.getServletContext().getInitParameter("encode");
		
	}
	//内部类
	class MyHttpServletRequest extends HttpServletRequestWrapper
	{
        private  HttpServletRequest request=null;
		public MyHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request=request;
		}
		
		 
		public Map<String,String[]> getParameterMap() 
		{
			try {
				if(request.getMethod().equalsIgnoreCase("POST"))
				{//如果是POST提交则一句话解决乱码问题
					request.setCharacterEncoding("UTF-8");
					return request.getParameterMap();
			    }
				else if (request.getMethod().equalsIgnoreCase("GET"))
				{//如果是GET方式提交，则先获取Map再进行遍历，在遍历中进行解决乱码问题；
					Map<String,String[]> map=request.getParameterMap();
					for(Map.Entry<String,String[]> entry:map.entrySet())
					{
						String [] vs=entry.getValue();
						if(isNotEncode){
							for(int i=0;i<vs.length;i++)
							{
								vs[i]=new String(vs[i].getBytes("ISO-8859-1"),encode);
							}
							isNotEncode=false;
						}
					}
					return map;
				}else{//除了POST和GET还有其他的提交方式，其他方式就直接返回原有形式
					return request.getParameterMap();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		
		}
		 public String[] getParameterValues(String name) 
		 {
				return getParameterMap().get(name);
		 }   
		 public String getParameter(String name) 
		 {
				return getParameterValues(name)==null?null:getParameterValues(name)[0];
		 }   

				
	}

}
