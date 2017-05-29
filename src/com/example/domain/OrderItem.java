package com.example.domain;

import java.io.Serializable;

public class OrderItem implements Serializable{
   private String order_id;
   private String product_id;
   private int buynum;
public String getOrder_id() {
	return order_id;
}
public void setOrder_id(String orderId) {
	order_id = orderId;
}
public String getProduct_id() {
	return product_id;
}
public void setProduct_id(String productId) {
	product_id = productId;
}
public int getBuynum() {
	return buynum;
}
public void setBuynum(int buynum) {
	this.buynum = buynum;
}
}
