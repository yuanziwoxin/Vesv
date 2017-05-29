package com.example.web;
/**
 * 生成购物订单
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.Orders;
import com.example.domain.OrderItem;
import com.example.domain.Product;
import com.example.domain.User;
import com.example.factory.BasicFactory;
import com.example.service.OrderService;
import org.apache.commons.beanutils.BeanUtils;

public class AddOrderServlet extends HttpServlet {
   
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  OrderService service=BasicFactory.getFactory().getService(OrderService.class);
		  try {
         //1.封装数据
		      Orders orders=new Orders();
		      //(1) 订单id
		      orders.setId(UUID.randomUUID().toString());
		      
		      Map<Product,Integer> cartmap=(Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		      //遍历cartmap(购物车)，从而获取订单金额和订单项记录
		      double money=0;//订单金额
		      List<OrderItem> list=new ArrayList<OrderItem>();//订单项记录
		      for(Map.Entry<Product, Integer> entry:cartmap.entrySet())
		      {
		    	  money+=entry.getKey().getPrice()*entry.getValue();//遍历时循环加上每种物品的总价，从而算出所有物品的总价
		          OrderItem item=new OrderItem();//订单项
		          item.setOrder_id(orders.getId());//将订单表的订单号赋给订单项表的订单id
		          item.setProduct_id(entry.getKey().getId());//将购物车中该种物品的商品id号赋给订单项表的商品id
		          item.setBuynum(entry.getValue());//将购物车中该种物品的购买数量赋给订单项表的购买数量
		          list.add(item);
		      }
		      //(2) 订单金额
		      orders.setMoney(money);
		      //(3) 收货地址
		      BeanUtils.populate(orders,request.getParameterMap());
			  //(4) 支付状态
		      orders.setPaystate(0);//先设置为未支付状态
			  //(5) 购买的用户id
		      User user=(User) request.getSession().getAttribute("user");
			  orders.setUser_id(user.getId());
			  //(6)订单项记录
			  orders.setList(list);
			  
			  //2.调用service的生成订单方法
			  service.addOrder(orders);
			  //3.清空购物车
			  cartmap.clear();
		      //4.3秒后跳转到主页
			  response.getWriter().write("订单成功生成！！！");
			  response.setHeader("Refresh","3;url=/Estore/index.jsp");
			 
			} catch (Exception e) {
				e.printStackTrace();
			    throw new RuntimeException(e);
			}
		  
		   
	}
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
