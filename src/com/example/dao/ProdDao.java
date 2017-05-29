package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.example.domain.Product;

public interface ProdDao extends Dao{
   /**
    * �����ݿ��������Ʒ��Ϣ��¼
    * @param product ��װ����Ʒ��Ϣ��bean����
    */
	void addProd(Product product);

	/**
	 * ��ѯ��������Ʒ��Ϣ
	 * @return ����һ����װ��һ������Ʒ��Ϣ��bean�����list����
	 */
    List<Product> findProd();
     /**
      * ͨ��id��ѯ��Ʒ��Ϣ
      * @param id ��Ʒid
      * @return  ����һ����װ����Ʒ��Ϣ��bean����
      */
	Product findProdById(String id);
     /**
      * ������Ʒ�������
      * @param buyNum  ���������������ٵ�������
      * @param productId  ��Ʒid
     * @throws SQLException 
      */
	void updateProdPnum(int buyNum, String productId) throws SQLException;
    /**
     * ������Ʒ�������
     * @param productId ��Ʒid
     * @param buynum  ���������������ӵ�������
     */
	void addProdPnum(String productId, int buynum);

}
