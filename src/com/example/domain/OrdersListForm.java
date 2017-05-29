package com.example.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class OrdersListForm implements Serializable{
	private String id;//订单号
	private String user_name;//用户名
    private double money;//订单金额
    private String receiverinfo;//收货地址
    private int paystate;//支付状态
    private Timestamp ordertime;//下单时间
    private Map<Product,Integer> prodMap;//商品信息
   // private List<OrderItem> list;//订单项纪录
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
