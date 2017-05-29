package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.example.annotation.Tran;
import com.example.domain.Orders;
import com.example.domain.OrderItem;
import com.example.domain.SaleList;

public interface OrderDao extends Dao{
   /**
    * 生成订单
    * @param orders  封装有订单信息的bean对象
    * @throws SQLException 
    */

	void addOrder(Orders orders) throws SQLException;
    /**
     * 生成订单项
     * @param connc
     * @param item  封装订单项信息的bean对象
     * @throws SQLException 
     */ 

   void addOrderItem(OrderItem item) throws SQLException;
   /**
    * 通过用户id查询出所有订单信息
    * @param userId 用户id
    * @return 返回封装有订单信息的bean对象（Orders）的list集合
    */
	List<Orders> findOrdersByUserId(int userId);
	/**
	 * 通过订单id查询出所有订单项
	 * @param order_id 订单id
	 * @return 返回封装有订单项信息的bean对象（OrderItem）的list集合
	 */
	List<OrderItem> findOrdersItemById(String order_id);
	/**
	 * 根据订单id删除其相应的所有的订单项记录
	 * @param id  订单id
	 */
	void delOrderItem(String id);
	/**
	 * 根据订单id删除订单
	 * @param id 订单号
	 */
	void delOrderById(String id);
	/**
	 * 根据订单id修改指定订单的支付状态
	 * @param orderId 订单id
	 * @param i 支付状态
	 */
	void changePayState(String orderId, int i);
	/**
	 * 根据订单id查询订单信息
	 * @param orderId 订单id
	 * @return 返回一个封装有订单信息的bean对象
	 */
	Orders findOrderById(String orderId);
	/**
	 * 查询出所有出售商品的信息
	 * @return 封装有出售商品信息的 bean对象的list集合
	 */
	List<SaleList> SaleList();
	

}
