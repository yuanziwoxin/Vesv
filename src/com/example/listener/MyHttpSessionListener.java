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
		//在session创建的时候就创建一个属性用于存储商品信息和购买数量
	   
		se.getSession().setAttribute("cartmap",new LinkedHashMap<Product,Integer>());
		
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	

}
