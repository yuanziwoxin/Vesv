package com.example.service;

import java.util.List;

import com.example.annotation.Tran;
import com.example.domain.Product;

public interface ProdService extends Service{

	/**
	 * �����Ʒ
	 * @param product ��װ����Ʒ��Ϣ��bean����
	 */
	
	void addProd(Product product);
    
	/**
	 * ��ȡ������Ʒ��Ϣ
	 * @return ����һ����װ��һ������Ʒ��Ϣ��bean�����list���ϣ���֮����null��
	 */
	List<Product> findProd();
    /**
     * ͨ��id��ȡ��Ʒ��Ϣ
     * @param id ��Ʒid
     * @return ����һ����װ����Ʒ��Ϣ��bean����
     */
	Product findProdById(String id);

}
