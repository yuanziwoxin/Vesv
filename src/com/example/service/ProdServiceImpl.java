package com.example.service;

import java.util.List;
import java.util.UUID;

import com.example.dao.ProdDao;
import com.example.domain.Product;
import com.example.factory.BasicFactory;

public class ProdServiceImpl implements ProdService {
    private ProdDao dao=BasicFactory.getFactory().getDao(ProdDao.class);
	public void addProd(Product product) {
		product.setId(UUID.randomUUID().toString());
		dao.addProd(product);//ÃÌº”…Ã∆∑		
	}
	public List<Product> findProd() {
		
		return dao.findProd();
	}
	public Product findProdById(String id) {
		
		return dao.findProdById(id);
	}

}
