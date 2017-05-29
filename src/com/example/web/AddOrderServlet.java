package com.example.web;
/**
 * ���ɹ��ﶩ��
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
         //1.��װ����
		      Orders orders=new Orders();
		      //(1) ����id
		      orders.setId(UUID.randomUUID().toString());
		      
		      Map<Product,Integer> cartmap=(Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		      //����cartmap(���ﳵ)���Ӷ���ȡ�������Ͷ������¼
		      double money=0;//�������
		      List<OrderItem> list=new ArrayList<OrderItem>();//�������¼
		      for(Map.Entry<Product, Integer> entry:cartmap.entrySet())
		      {
		    	  money+=entry.getKey().getPrice()*entry.getValue();//����ʱѭ������ÿ����Ʒ���ܼۣ��Ӷ����������Ʒ���ܼ�
		          OrderItem item=new OrderItem();//������
		          item.setOrder_id(orders.getId());//��������Ķ����Ÿ����������Ķ���id
		          item.setProduct_id(entry.getKey().getId());//�����ﳵ�и�����Ʒ����Ʒid�Ÿ�������������Ʒid
		          item.setBuynum(entry.getValue());//�����ﳵ�и�����Ʒ�Ĺ������������������Ĺ�������
		          list.add(item);
		      }
		      //(2) �������
		      orders.setMoney(money);
		      //(3) �ջ���ַ
		      BeanUtils.populate(orders,request.getParameterMap());
			  //(4) ֧��״̬
		      orders.setPaystate(0);//������Ϊδ֧��״̬
			  //(5) ������û�id
		      User user=(User) request.getSession().getAttribute("user");
			  orders.setUser_id(user.getId());
			  //(6)�������¼
			  orders.setList(list);
			  
			  //2.����service�����ɶ�������
			  service.addOrder(orders);
			  //3.��չ��ﳵ
			  cartmap.clear();
		      //4.3�����ת����ҳ
			  response.getWriter().write("�����ɹ����ɣ�����");
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
