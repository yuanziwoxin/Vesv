package com.example.domain;

import java.io.Serializable;

public class SaleList implements Serializable{
  private String prod_id;//���۵���Ʒ���
  private String prod_name;//���۵���Ʒ����
  private int sale_num;//���۵���Ʒ����
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
