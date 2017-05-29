package com.example.util;
/**
 * 1.利用第三方进行事务处理，从而进行解耦；
 * 2.问题：当多个操作需要进行事务操作时，都调用这一个第三方类的方法进行处理时又会造成线程安全问题(程序并发时就考虑线程安全问题)，
 *    例子：（例：有可能有些线程刚开启事务，而有些线程可能快提交事务），
 *    解决：所以应该进行利用ThreadLocal类（该类提供了线程局部 (thread-local) 变量。这些变量不同于它们的普通对应物，因为访问某个变量（通过其 get 或 set 方法）的每个线程都有自己的局部变量，它独立于变量的初始化副本。ThreadLocal 实例通常是类中的 private static 字段，它们希望将状态与某一个线程（例如，用户 ID 或事务 ID）相关联。 ）
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TransactionManager {
      private TransactionManager(){
    	  
      }
      //整个程序只有这一个数据源
      private static DataSource source=new ComboPooledDataSource();//创建数据源
      //设置一个Boolean的值用于判断是否开启事务，初始值为未开启状态；
      private static ThreadLocal<Boolean> flag_local=new ThreadLocal<Boolean>(){
    	  @Override
    	  protected Boolean initialValue() {
			
    		  return false;//默认不开启事务
    		  
    	  };
      };
      //保存真实连接的代理连接，改造过close方法
      private static ThreadLocal<Connection> proxyConnc_local=new ThreadLocal<Connection>(){};
      //保存真实连接
      private static ThreadLocal<Connection> realConnc_local=new ThreadLocal<Connection>(){};
    
   /**
       * 获取数据连接池的连接
       * @return 一个连接
       */
      /*
      public static Connection getConnc()
      {
    	  return  connc_local.get();
      }*/
      /**
       * 开启事务方法
     * @throws SQLException 
       */
      public static void StartTran() throws SQLException
      {
    	  //connc_local.get().setAutoCommit(false);
    	  flag_local.set(true);//设置为true
    	  final Connection connc=source.getConnection();
		  connc.setAutoCommit(false);//开启事务
		  realConnc_local.set(connc);//为了方便后续关闭连接操作，将这个连接保存在当前线程中；
		  Connection proxyConnc=(Connection) Proxy.newProxyInstance(connc.getClass().getClassLoader(),connc.getClass().getInterfaces(),new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				     if("close".equals(method.getName())){
				    	 //如果是close方法就进行改造，使他不能关闭连接
				    	 return null;//
				     }else{
				    	 //不是就返回原方法
				    	 return method.invoke(connc, args);
				    	
				     }
				  }
		});
			  proxyConnc_local.set(proxyConnc); //真实连接的代理连接
		 }
      /**
       * 事务提交方法
       */
      public static void commit()
      {
    	  DbUtils.commitAndCloseQuietly(proxyConnc_local.get());
      }
      /**
       * 事务回滚方法
       */
      public static void rollback()
      {
    	  DbUtils.rollbackAndCloseQuietly(proxyConnc_local.get());
      }
      /**
       *  1.如果没有开启过事务，则返回普通的数据源
       *  2.如果开启过事务，则返回一个改造过的getConnection方法的数据源，这个方法每次都返回一个开启过相同事务的Connection；
       * @throws SQLException 
       */
      public static DataSource getSource() throws SQLException
      {
    	  if(flag_local.get()){//如果开启过事务，则改造getConnection()方法
    		
    		  return  (DataSource)Proxy.newProxyInstance(source.getClass().getClassLoader(), source.getClass().getInterfaces(),new InvocationHandler() {
				
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
                        if("getConnection".equals(method.getName()))
                        {//如果调用getConnection()方法，则调用开启相同事务且改造过的getConnection方法
                        	return proxyConnc_local.get();
                        }else{
                        	return method.invoke(source, args);//反之则直接调用数据源即可
                        }				
				  }
			});  
    	  }else{
    			return source;//没有开启事务则直接调用普通数据源；
    	  }

      }
      /**
       * 
       * 资源释放(线程是有限的资源，用完之后应及时释放)
       */
      
      public static void release()
      {
    	  DbUtils.closeQuietly(realConnc_local.get());//之前连接没有关闭，这里才是真正的关闭连接
    	  realConnc_local.remove();//释放资源
    	  flag_local.remove();
    	  proxyConnc_local.remove();
      }
}
