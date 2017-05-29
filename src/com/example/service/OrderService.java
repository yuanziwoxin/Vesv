package com.example.service;

import java.sql.SQLException;
import java.util.List;

import com.example.annotation.Tran;
import com.example.domain.Orders;
import com.example.domain.OrdersListForm;
import com.example.domain.SaleList;

public interface OrderService extends Service{
   /**
    * ���ɶ���
    * @param orders ��װ�ж�����Ϣ��bean����
 * @throws SQLException 
    */
	@Tran
	void addOrder(Orders orders);
  /**
   * ��ѯ������Ϣ
   * @param userId ����id
   * @return ���� һ����װ�ж�����Ϣ��bean�����list���ϣ���֮����null��
   */
List<OrdersListForm> GetOrdersById(int userId);
/**
 * ���ݶ���idɾ������
 * @param id ������id
 */
  @Tran  //��Ҫʹ���������
  void DelOrderById(String id);
  /**
   * ͨ�������Ż�ȡ������Ϣ
   * @param orderId ������id
   * @return ����һ����װ�ж�����Ϣ��bean��Orders��
   */
Orders findOrderById(String orderId);
/**
 * ���ݶ���id�޸�ָ���Ķ�����֧��״̬
 * @param orderId  ����id
 * @param i ����֧��״̬�����֣���1��������֧������0������δ֧��
 */
void changePayState(String orderId, int i);
/**
 * ��ȡ������Ʒ����Ϣ
 * @return ��װ�г�����Ʒ��Ϣ��bean�����list����
 */
List<SaleList> saleList();

}
