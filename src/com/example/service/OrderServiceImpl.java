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
				//1.���ɶ���
		    	orderDao.addOrder(orders);
		    	//2.���ɶ�������޸���Ʒ���
		    	List<OrderItem> list=orders.getList();
		    	for(OrderItem item:list)
		    	{
		    		//(1) ���ɶ�����
		    		orderDao.addOrderItem(item);
		    		//(2) �޸���Ʒ���
		    		String productId=item.getProduct_id();//��ȡ��������Ʒ��id
		    		int buyNum=item.getBuynum();//��ȡ���������
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
		//1.ͨ���û�id��ȡ���û������ж�����Ϣ
		List<Orders> list=orderDao.findOrdersByUserId(userId);
	    //2.�������ж�����Ϣ������OrdersListForm���󣬴���list������
				for(Orders order:list)
				{ //(1)���ö�����Ϣ
					OrdersListForm olf=new OrdersListForm();
					BeanUtils.copyProperties(olf,order);//���������е���Ϣ���Ƶ�OrdersListForm������
					//(2)�����û���
					User user=userDao.findUserById(order.getUser_id());//ͨ���û�id��ѯ���û���
					olf.setUser_name(user.getUsername());//�����û����������б��bean��
					//(3)������Ʒ��Ϣ
					
					Map<Product,Integer> prodMap=new HashMap<Product,Integer>();//��һ��Map�洢��Ʒ��Ϣ�͹�������
					 // ---��ѯ��ǰ���������ж�����
					List<OrderItem> orderItem=orderDao.findOrdersItemById(order.getId());
					// ----�������ж�����
					for(OrderItem item:orderItem)
					{
						Product prod=prodDao.findProdById(item.getProduct_id());//ͨ����Ʒid��ѯ��Ʒ��Ϣ��
					    prodMap.put(prod,item.getBuynum());//����Ʒ��Ϣ�͹��������洢��Map�����У�
					}
			        olf.setProdMap(prodMap);//���洢��Ʒ��Ϣ��Map���õ�OrdersItemForm��
			        olfList.add(olf);//��OrderItemForm����洢��list������
			    }
		   return olfList;
	    }catch(Exception e) {
		e.printStackTrace();
	    throw new RuntimeException(e);
	}
	
  }
	public void DelOrderById(String id) {
		//1.���ݶ���id��ȡ�����ж����һ�����������ж�����Ʒ��Ҳ�������������¼��
	    List<OrderItem> list= orderDao.findOrdersItemById(id);
		//2.�������ж����� / ��������Ӧɾ�������е���Ʒ���
	        //(1)�������ж�����
	        for(OrderItem item:list)
	        {
	        	//(2)���ӿ��
	        	prodDao.addProdPnum(item.getProduct_id(),item.getBuynum());
	        }
		//3.ɾ�������������ɾ���������Ϊ�������ж������������������ɾ����
	       orderDao.delOrderItem(id);
		//4.ɾ������
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
