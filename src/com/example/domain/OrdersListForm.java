package com.example.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class OrdersListForm implements Serializable{
	private String id;//������
	private String user_name;//�û���
    private double money;//�������
    private String receiverinfo;//�ջ���ַ
    private int paystate;//֧��״̬
    private Timestamp ordertime;//�µ�ʱ��
    private Map<Product,Integer> prodMap;//��Ʒ��Ϣ
   // private List<OrderItem> list;//�������¼
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReceiverinfo() {
		return receiverinfo;
	}
	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}
	public int getPaystate() {
		return paystate;
	}
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}
	public Timestamp getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}
	public Map<Product, Integer> getProdMap() {
		return prodMap;
	}
	public void setProdMap(Map<Product, Integer> prodMap) {
		this.prodMap = prodMap;
	}
}
