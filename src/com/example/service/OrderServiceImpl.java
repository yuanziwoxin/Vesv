package com.example.service;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.annotation.Tran;
import com.example.dao.OrderDao;
import com.example.dao.ProdDao;
import com.example.dao.UserDao;
import com.example.domain.Orders;
import com.example.domain.OrderItem;
import com.example.domain.OrdersListForm;
import com.example.domain.Product;
import com.example.domain.SaleList;
import com.example.domain.User;
import com.example.factory.BasicFactory;
import org.apache.commons.beanutils.BeanUtils;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao=BasicFactory.getFactory().getDao(OrderDao.class);
	ProdDao prodDao=BasicFactory.getFactory().getDao(ProdDao.class);
    UserDao userDao=BasicFactory.getFactory().getDao(UserDao.class);
	@Tran
    public void addOrder(Orders orders){
    	
    	     try{
				//1.生成订单
		    	orderDao.addOrder(orders);
		    	//2.生成订单项和修改商品库存
		    	List<OrderItem> list=orders.getList();
		    	for(OrderItem item:list)
		    	{
		    		//(1) 生成订单项
		    		orderDao.addOrderItem(item);
		    		//(2) 修改商品库存
		    		String productId=item.getProduct_id();//获取被购买商品的id
		    		int buyNum=item.getBuynum();//获取购买的数量
		    		prodDao.updateProdPnum(buyNum,productId);
		         }
    	     }catch(Exception e){
    	    	 e.printStackTrace();
    	    	 throw new RuntimeException(e);
    	     }
 
		
	}
	public List<OrdersListForm> GetOrdersById(int userId) {
		try {
		
		List<OrdersListForm> olfList=new ArrayList<OrdersListForm>();
		//1.通过用户id获取该用户的所有订单信息
		List<Orders> list=orderDao.findOrdersByUserId(userId);
	    //2.遍历所有订单信息，生成OrdersListForm对象，存入list集合中
				for(Orders order:list)
				{ //(1)设置订单信息
					OrdersListForm olf=new OrdersListForm();
					BeanUtils.copyProperties(olf,order);//将订单表中的信息复制到OrdersListForm对象中
					//(2)设置用户名
					User user=userDao.findUserById(order.getUser_id());//通过用户id查询到用户名
					olf.setUser_name(user.getUsername());//设置用户名至订单列表的bean中
					//(3)设置商品信息
					
					Map<Product,Integer> prodMap=new HashMap<Product,Integer>();//用一个Map存储商品信息和购买数量
					 // ---查询当前订单的所有订单项
					List<OrderItem> orderItem=orderDao.findOrdersItemById(order.getId());
					// ----遍历所有订单项
					for(OrderItem item:orderItem)
					{
						Product prod=prodDao.findProdById(item.getProduct_id());//通过商品id查询商品信息；
					    prodMap.put(prod,item.getBuynum());//将商品信息和购买数量存储到Map集合中；
					}
			        olf.setProdMap(prodMap);//将存储商品信息的Map设置到OrdersItemForm中
			        olfList.add(olf);//将OrderItemForm对象存储到list集合中
			    }
		   return olfList;
	    }catch(Exception e) {
		e.printStackTrace();
	    throw new RuntimeException(e);
	}
	
  }
	public void DelOrderById(String id) {
		//1.根据订单id获取其所有订单项（一个订单可能有多种商品，也即多条订单项记录）
	    List<OrderItem> list= orderDao.findOrdersItemById(id);
		//2.遍历所有订单项 / 并增加相应删除订单中的商品库存
	        //(1)遍历所有订单项
	        for(OrderItem item:list)
	        {
	        	//(2)增加库存
	        	prodDao.addProdPnum(item.getProduct_id(),item.getBuynum());
	        }
		//3.删除订单项（必须先删除订单项，因为订单表有订单项表的外键，不能先删除）
	       orderDao.delOrderItem(id);
		//4.删除订单
	       orderDao.delOrderById(id);
	}
	public void changePayState(String orderId, int i) {
		orderDao.changePayState(orderId,i);
		
	}
	public Orders findOrderById(String orderId) {
		
		return orderDao.findOrderById(orderId);
	}
	public List<SaleList> saleList() {
		
		return orderDao.SaleList();
	}
}
