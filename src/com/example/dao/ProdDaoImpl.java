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
		String sql="update products set pnum=pnum-? where id=? and pnum-?>=0";//pnum-?>=0当库存数量不小于购买数量才能购买成功；
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		int count=runner.update( sql,buyNum,productId,buyNum);
		if(count<=0)
		{//改变的行数小于或等于0，即没有改变库存数量时，说明购买数量超过了库存数量，抛出异常使得事务回滚，从而使得订单生成失败；
			throw new SQLException("商品数量不够！");
		}
	}
    /*
     * 注意：运行时异常将由运行时系统自动抛出，不需要使用throw语句
     * 所以：这是为何异常没有向上抛，事务管理依然能收到异常并触发事务回滚
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
