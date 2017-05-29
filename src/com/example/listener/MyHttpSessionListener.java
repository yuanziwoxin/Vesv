package com.example.listener;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.example.domain.Product;

public class MyHttpSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		//��session������ʱ��ʹ���һ���������ڴ洢��Ʒ��Ϣ�͹�������
	   
		se.getSession().setAttribute("cartmap",new LinkedHashMap<Product,Integer>());
		
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	

}
