package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.example.domain.OrderItem;
import com.example.domain.User;
import com.example.util.MD5Utils;
import com.example.util.TransactionManager;

public class UserDaoImpl implements UserDao{

	public void addUser(User user) {
		try {
		String sql="insert into users values(null,?,?,?,?,?,?,?,null)";
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		runner.update(sql,user.getUsername(),MD5Utils.md5(user.getPassword()),user.getNickname(),user.getEmail(),user.getRole(),user.getState(),user.getActivecode());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public User findUserByUsername(String username) {
		User user=null;
		try {
		String sql="select * from users where username=?";
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		user=runner.query(sql,new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return user;
	}

	public User findUserByActivecode(String activecode) {
		User user=null;
		try {
		String sql="select * from users where activecode=?";
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		user=runner.query(sql,new BeanHandler<User>(User.class),activecode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return user;
	}

	public void updateState(int id, int state) {
		try {
		  String sql="update users set state=? where id=?";
		  QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		  runner.update(sql, state,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public void deleteUser(int id) {
		try {
		   String sql="delete from users where id=?";
		   QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		   runner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public User findUserByNameAndPwd(String username, String password) {
		User user=null;
		try {
		String sql="select * from users where username=? and password=?";
		QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		user=runner.query(sql,new BeanHandler<User>(User.class),username,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return user;
	}

	public User findUserById(int userId) {
		String sql="select * from users where id=?";
		try {
		 	QueryRunner runner=new QueryRunner(TransactionManager.getSource());
		    return runner.query(sql,new BeanHandler<User>(User.class),userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	    
	}

}
