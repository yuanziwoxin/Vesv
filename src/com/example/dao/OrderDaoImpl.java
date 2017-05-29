package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.example.domain.Orders;
import com.example.domain.OrderItem;
import com.example.domain.SaleList;
import com.example.util.TransactionManager;

public class OrderDaoImpl implements OrderDao {

	public void addOrder(Orders orders) throws SQLException {
		String  sql="insert into orders  values(?,?,?,?,null,?)";
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		runner.update(sql, orders.getId(),orders.getMoney(),orders.getReceiverinfo(),orders.getPaystate(),orders.getUser_id());
		
	}

	public void addOrderItem(OrderItem item) throws SQLException {
		String  sql="insert into orderitem values(?,?,?)";
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		runner.update(sql,item.getOrder_id(),item.getProduct_id(),item.getBuynum());
		
	}

	public List<Orders> findOrdersByUserId(int userId) {
		String sql="select * from orders where user_id=?";
		try {
		 	QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		    return runner.query(sql,new BeanListHandler<Orders>(Orders.class),userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public List<OrderItem> findOrdersItemById(String order_id) {
		String sql="select * from orderitem where order_id=?";
		try {
		 	QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		    return runner.query(sql,new BeanListHandler<OrderItem>(OrderItem.class),order_id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
    /*
     * ��ʵ�ϣ�������Ϊ0����ArithmeticException����RuntimException�����ࡣ������ʱ�쳣��������ʱϵͳ�Զ��׳�������Ҫʹ��throw��䡣
     * ����ʱ�쳣��������ʱϵͳ�Զ��׳�������Ҫʹ��throw���
     */
	public void delOrderById(String id) {//??Ϊ���쳣û�������ף����������Ȼ���յ��쳣����������ع�
	   String sql="delete from orders where id=?";
	   try {
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
	   // int  i=1/0;//���ڲ���֮ǰд����������Ƿ�����
		runner.update(sql,id);
	 } catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
		
	}
     /*
      *   
      * ע�⣺����ʱ�쳣��������ʱϵͳ�Զ��׳�������Ҫʹ��throw���
      * ���ԣ�����Ϊ���쳣û�������ף����������Ȼ���յ��쳣����������ع�
      */
     
	public void delOrderItem(String id) {

		   String sql="delete from orderitem where order_id=?";
		   try {
			QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		    runner.update(sql,id);
		 } catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void changePayState(String orderId, int i) {
		
		    String sql="update orders set paystate=? where id=?";
       try {   
		    QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		    runner.update(sql,i,orderId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public Orders findOrderById(String orderId) {
		
		String sql="select * from orders where id=?";
		try {
			QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		    return runner.query(sql,new BeanHandler<Orders>(Orders.class),orderId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public List<SaleList> SaleList() {
		String sql="select products.id prod_id,products.name prod_name,sum(orderitem.buynum) sale_num "+
                        " from orders,orderitem,products "+
                        " where orders.id=orderitem.order_id and orderitem.product_id=products.id "+
                        " and orders.paystate=1 "+
                        " group by products.id  "+
                        " order by sale_num desc ";
		try {
			QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		     return runner.query(sql,new BeanListHandler<SaleList>(SaleList.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
       
}
