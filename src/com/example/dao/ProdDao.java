package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.example.domain.Product;

public interface ProdDao extends Dao{
   /**
    * 向数据库中添加商品信息记录
    * @param product 封装有商品信息的bean对象
    */
	void addProd(Product product);

	/**
	 * 查询出所有商品信息
	 * @return 返回一个封装有一条条商品信息的bean对像的list集合
	 */
    List<Product> findProd();
     /**
      * 通过id查询商品信息
      * @param id 商品id
      * @return  返回一个封装有商品信息的bean对象
      */
	Product findProdById(String id);
     /**
      * 减少商品库存数量
      * @param buyNum  购买数量（即减少的数量）
      * @param productId  商品id
     * @throws SQLException 
      */
	void updateProdPnum(int buyNum, String productId) throws SQLException;
    /**
     * 增加商品库存数量
     * @param productId 商品id
     * @param buynum  购买数量（即增加的数量）
     */
	void addProdPnum(String productId, int buynum);

}
