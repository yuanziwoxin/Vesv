package com.example.util;
/**
 * 1.���õ����������������Ӷ����н��
 * 2.���⣺�����������Ҫ�����������ʱ����������һ����������ķ������д���ʱ�ֻ�����̰߳�ȫ����(���򲢷�ʱ�Ϳ����̰߳�ȫ����)��
 *    ���ӣ��������п�����Щ�̸߳տ������񣬶���Щ�߳̿��ܿ��ύ���񣩣�
 *    ���������Ӧ�ý�������ThreadLocal�ࣨ�����ṩ���ֲ߳̾� (thread-local) ��������Щ������ͬ�����ǵ���ͨ��Ӧ���Ϊ����ĳ��������ͨ���� get �� set ��������ÿ���̶߳����Լ��ľֲ��������������ڱ����ĳ�ʼ��������ThreadLocal ʵ��ͨ�������е� private static �ֶΣ�����ϣ����״̬��ĳһ���̣߳����磬�û� ID ������ ID��������� ��
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
      //��������ֻ����һ������Դ
      private static DataSource source=new ComboPooledDataSource();//��������Դ
      //����һ��Boolean��ֵ�����ж��Ƿ������񣬳�ʼֵΪδ����״̬��
      private static ThreadLocal<Boolean> flag_local=new ThreadLocal<Boolean>(){
    	  @Override
    	  protected Boolean initialValue() {
			
    		  return false;//Ĭ�ϲ���������
    		  
    	  };
      };
      //������ʵ���ӵĴ������ӣ������close����
      private static ThreadLocal<Connection> proxyConnc_local=new ThreadLocal<Connection>(){};
      //������ʵ����
      private static ThreadLocal<Connection> realConnc_local=new ThreadLocal<Connection>(){};
    
   /**
       * ��ȡ�������ӳص�����
       * @return һ������
       */
      /*
      public static Connection getConnc()
      {
    	  return  connc_local.get();
      }*/
      /**
       * �������񷽷�
     * @throws SQLException 
       */
      public static void StartTran() throws SQLException
      {
    	  //connc_local.get().setAutoCommit(false);
    	  flag_local.set(true);//����Ϊtrue
    	  final Connection connc=source.getConnection();
		  connc.setAutoCommit(false);//��������
		  realConnc_local.set(connc);//Ϊ�˷�������ر����Ӳ�������������ӱ����ڵ�ǰ�߳��У�
		  Connection proxyConnc=(Connection) Proxy.newProxyInstance(connc.getClass().getClassLoader(),connc.getClass().getInterfaces(),new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				     if("close".equals(method.getName())){
				    	 //�����close�����ͽ��и��죬ʹ�����ܹر�����
				    	 return null;//
				     }else{
				    	 //���Ǿͷ���ԭ����
				    	 return method.invoke(connc, args);
				    	
				     }
				  }
		});
			  proxyConnc_local.set(proxyConnc); //��ʵ���ӵĴ�������
		 }
      /**
       * �����ύ����
       */
      public static void commit()
      {
    	  DbUtils.commitAndCloseQuietly(proxyConnc_local.get());
      }
      /**
       * ����ع�����
       */
      public static void rollback()
      {
    	  DbUtils.rollbackAndCloseQuietly(proxyConnc_local.get());
      }
      /**
       *  1.���û�п����������򷵻���ͨ������Դ
       *  2.��������������򷵻�һ���������getConnection����������Դ���������ÿ�ζ�����һ����������ͬ�����Connection��
       * @throws SQLException 
       */
      public static DataSource getSource() throws SQLException
      {
    	  if(flag_local.get()){//������������������getConnection()����
    		
    		  return  (DataSource)Proxy.newProxyInstance(source.getClass().getClassLoader(), source.getClass().getInterfaces(),new InvocationHandler() {
				
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
                        if("getConnection".equals(method.getName()))
                        {//�������getConnection()����������ÿ�����ͬ�����Ҹ������getConnection����
                        	return proxyConnc_local.get();
                        }else{
                        	return method.invoke(source, args);//��֮��ֱ�ӵ�������Դ����
                        }				
				  }
			});  
    	  }else{
    			return source;//û�п���������ֱ�ӵ�����ͨ����Դ��
    	  }

      }
      /**
       * 
       * ��Դ�ͷ�(�߳������޵���Դ������֮��Ӧ��ʱ�ͷ�)
       */
      
      public static void release()
      {
    	  DbUtils.closeQuietly(realConnc_local.get());//֮ǰ����û�йرգ�������������Ĺر�����
    	  realConnc_local.remove();//�ͷ���Դ
    	  flag_local.remove();
    	  proxyConnc_local.remove();
      }
}
