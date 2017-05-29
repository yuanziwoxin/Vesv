package com.example.service;

import java.util.List;

import com.example.annotation.Tran;
import com.example.domain.Product;

public interface ProdService extends Service{

	/**
	 * 添加商品
	 * @param product 封装有商品信息的bean对象
	 */
	
	void addProd(Product product);
    
	/**
	 * 获取所有商品信息
	 * @return 返回一个封装有一条条商品信息的bean对象的list集合，反之返回null；
	 */
	List<Product> findProd();
    /**
     * 通过id获取商品信息
     * @param id 商品id
     * @return 返回一个封装有商品信息的bean对象
     */
	Product findProdById(String id);

}
