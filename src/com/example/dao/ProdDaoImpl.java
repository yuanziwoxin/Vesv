package com.example.dao;


import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.example.domain.Product;
import com.example.util.TransactionManager;

public class ProdDaoImpl implements ProdDao {

	public void addProd(Product product) {
		try {
			String sql="insert into products values(?,?,?,?,?,?,?)";
			QueryRunner runner=new QueryRunner(TransactionManager.getSource());
	        runner.update(sql,product.getId(),product.getName(),product.getPrice(),product.getCategory(),product.getPnum(),product.getImgurl(),product.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public List<Product> findProd() {
	  try {
		String sql="select * from products";
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		return runner.query(sql,new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public Product findProdById(String id) {
		 try {
				String sql="select * from products where id=?";
				QueryRunner runner=new QueryRunner(TransactionManager.getSource());
				return runner.query(sql,new BeanHandler<Product>(Product.class),id);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
	}

	public void updateProdPnum(int buyNum, String productId) throws SQLException {
		String sql="update products set pnum=pnum-? where id=? and pnum-?>=0";//pnum-?>=0�����������С�ڹ����������ܹ���ɹ���
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		int count=runner.update( sql,buyNum,productId,buyNum);
		if(count<=0)
		{//�ı������С�ڻ����0����û�иı�������ʱ��˵���������������˿���������׳��쳣ʹ������ع����Ӷ�ʹ�ö�������ʧ�ܣ�
			throw new SQLException("��Ʒ����������");
		}
	}
    /*
     * ע�⣺����ʱ�쳣��������ʱϵͳ�Զ��׳�������Ҫʹ��throw���
     * ���ԣ�����Ϊ���쳣û�������ף����������Ȼ���յ��쳣����������ع�
     */
	public void addProdPnum(String productId, int buynum) {
		try{
		String sql="update products set pnum=pnum+? where id=?";
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		runner.update( sql,buynum,productId);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}
