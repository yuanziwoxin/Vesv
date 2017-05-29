package com.example.domain;

import java.io.Serializable;

public class SaleList implements Serializable{
  private String prod_id;//销售的商品编号
  private String prod_name;//销售的商品名称
  private int sale_num;//销售的商品数量
public String getProd_id() {
	return prod_id;
}
public void setProd_id(String prodId) {
	prod_id = prodId;
}
public String getProd_name() {
	return prod_name;
}
public void setProd_name(String prodName) {
	prod_name = prodName;
}
public int getSale_num() {
	return sale_num;
}
public void setSale_num(int saleNum) {
	sale_num = saleNum;
}
}
