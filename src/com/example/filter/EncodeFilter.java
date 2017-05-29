package com.example.filter;
/**
 * ���ȫվ��������
 *   װ�����ģʽ���ڲ��࣬Map<T,V>
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
		//���response��Ӧ����������
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset="+encode);
		//����װ�����ģʽ�ı�request����ͻ�ȡ���������صķ������Ӷ����request��POST��GET��ʽ���������⣻
		 chain.doFilter(new MyHttpServletRequest((HttpServletRequest) request), response);//����
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config=filterConfig;
		this.context=filterConfig.getServletContext();
		this.encode=filterConfig.getServletContext().getInitParameter("encode");
		
	}
	//�ڲ���
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
				{//�����POST�ύ��һ�仰�����������
					request.setCharacterEncoding("UTF-8");
					return request.getParameterMap();
			    }
				else if (request.getMethod().equalsIgnoreCase("GET"))
				{//�����GET��ʽ�ύ�����Ȼ�ȡMap�ٽ��б������ڱ����н��н���������⣻
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
				}else{//����POST��GET�����������ύ��ʽ��������ʽ��ֱ�ӷ���ԭ����ʽ
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
