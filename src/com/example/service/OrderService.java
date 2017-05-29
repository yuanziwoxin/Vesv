package com.example.service;

import java.sql.SQLException;
import java.util.List;

import com.example.annotation.Tran;
import com.example.domain.Orders;
import com.example.domain.OrdersListForm;
import com.example.domain.SaleList;

public interface OrderService extends Service{
   /**
    * 生成订单
    * @param orders 封装有订单信息的bean对象
 * @throws SQLException 
    */
	@Tran
	void addOrder(Orders orders);
  /**
   * 查询订单信息
   * @param userId 订单id
   * @return 返回 一个封装有订单信息的bean对象的list集合，反之返回null；
   */
List<OrdersListForm> GetOrdersById(int userId);
/**
 * 根据订单id删除订单
 * @param id 订单的id
 */
  @Tran  //需要使用事务管理
  void DelOrderById(String id);
  /**
   * 通过订单号获取订单信息
   * @param orderId 订单号id
   * @return 返回一个封装有订单信息的bean（Orders）
   */
Orders findOrderById(String orderId);
/**
 * 根据订单id修改指定的订单的支付状态
 * @param orderId  订单id
 * @param i 代表支付状态的数字，“1”代表已支付，“0”代表未支付
 */
void changePayState(String orderId, int i);
/**
 * 获取出售商品的信息
 * @return 封装有出售商品信息的bean对象的list集合
 */
List<SaleList> saleList();

}
