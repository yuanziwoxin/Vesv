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
     * 事实上，“除数为0”等ArithmeticException，是RuntimException的子类。而运行时异常将由运行时系统自动抛出，不需要使用throw语句。
     * 运行时异常将由运行时系统自动抛出，不需要使用throw语句
     */
	public void delOrderById(String id) {//??为何异常没有向上抛，事务管理依然能收到异常并触发事务回滚
	   String sql="delete from orders where id=?";
	   try {
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
	   // int  i=1/0;//用于测试之前写的事务管理是否有用
		runner.update(sql,id);
	 } catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
		
	}
     /*
      *   
      * 注意：运行时异常将由运行时系统自动抛出，不需要使用throw语句
      * 所以：这是为何异常没有向上抛，事务管理依然能收到异常并触发事务回滚
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
