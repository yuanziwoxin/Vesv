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
    * ���ɶ���
    * @param orders  ��װ�ж�����Ϣ��bean����
    * @throws SQLException 
    */

	void addOrder(Orders orders) throws SQLException;
    /**
     * ���ɶ�����
     * @param connc
     * @param item  ��װ��������Ϣ��bean����
     * @throws SQLException 
     */ 

   void addOrderItem(OrderItem item) throws SQLException;
   /**
    * ͨ���û�id��ѯ�����ж�����Ϣ
    * @param userId �û�id
    * @return ���ط�װ�ж�����Ϣ��bean����Orders����list����
    */
	List<Orders> findOrdersByUserId(int userId);
	/**
	 * ͨ������id��ѯ�����ж�����
	 * @param order_id ����id
	 * @return ���ط�װ�ж�������Ϣ��bean����OrderItem����list����
	 */
	List<OrderItem> findOrdersItemById(String order_id);
	/**
	 * ���ݶ���idɾ������Ӧ�����еĶ������¼
	 * @param id  ����id
	 */
	void delOrderItem(String id);
	/**
	 * ���ݶ���idɾ������
	 * @param id ������
	 */
	void delOrderById(String id);
	/**
	 * ���ݶ���id�޸�ָ��������֧��״̬
	 * @param orderId ����id
	 * @param i ֧��״̬
	 */
	void changePayState(String orderId, int i);
	/**
	 * ���ݶ���id��ѯ������Ϣ
	 * @param orderId ����id
	 * @return ����һ����װ�ж�����Ϣ��bean����
	 */
	Orders findOrderById(String orderId);
	/**
	 * ��ѯ�����г�����Ʒ����Ϣ
	 * @return ��װ�г�����Ʒ��Ϣ�� bean�����list����
	 */
	List<SaleList> SaleList();
	

}
