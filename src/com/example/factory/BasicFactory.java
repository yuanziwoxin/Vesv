package com.example.factory;
/**
 * 1.动态代理方法；
 * 2.将Service和Dao进行分离；
 */
import java.io.FileReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

import com.example.annotation.Tran;
import com.example.dao.Dao;
import com.example.service.Service;
import com.example.util.TransactionManager;

public class BasicFactory {
	private static BasicFactory factory=new BasicFactory();
	private static Properties properties=null;
    private BasicFactory(){}
    static{
    	properties=new Properties();
    	try {
			properties.load(new FileReader(BasicFactory.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
    public static BasicFactory getFactory()
    {
		return factory;
    }
  //根据引用的Dao，返回它的具体的实现方法
    public <T extends Dao> T getDao(Class<T> clazz)
    {
   	try {
   	String infName=clazz.getSimpleName();//取得它的接口名
   	String imlName=properties.getProperty(infName);//取得它的实现方法名
   	return (T) Class.forName(imlName).newInstance();//返回具体的实现的方法；
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
    
    //根据引用的Service，返回它的具体的实现方法
    @SuppressWarnings("unchecked")
	public <T extends Service> T getService(Class<T> clazz)
    {
   	try {
   //根据配置文件创建具体的service
   	String infName=clazz.getSimpleName();//取得它的接口名
   	String imlName=properties.getProperty(infName);//取得它的实现方法名
   	final T service=(T) Class.forName(imlName).newInstance();//返回具体的实现的方法；
	//为了实现AOP(面向切面编程)，生成service代理，使得可以根据注解在service方法之前和之后可以执行一些操作 （如：事务管理、记录日志、细粒度控制）
   	T proxyService=(T) Proxy.newProxyInstance(service.getClass().getClassLoader(),service.getClass().getInterfaces(),new InvocationHandler() {
		//根据注解进行事务管理
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			        if(method.isAnnotationPresent(Tran.class))
			        {//如果方法前有名为Tran的注解标记，则使用事务
			        	  try{
						         TransactionManager.StartTran();//开启事务（在调用service方法之前）
								 
						         Object obj=method.invoke(service, args);//真正执行的方法
								 
						         TransactionManager.commit();//提交事务（在service方法结束之后）
								 return obj;
						        }catch(InvocationTargetException e){//来自底层的异常
						        	TransactionManager.rollback();//回滚事务（发生异常）
						        	throw new RuntimeException(e.getTargetException());//来自底层的异常，即这里表示来自method的包装的service方法抛出的异常
						        }catch(Exception e){
						        	TransactionManager.rollback();//回滚事务（发生异常）
						        	throw new RuntimeException(e);
						        }finally{
						        	TransactionManager.release();//释放资源；
						        }
			        }else{//没有注解，不使用事务，直接执行方法；
			            //修改
			            try {
			                return method.invoke(service, args);
			              } catch (InvocationTargetException ite) {
			                throw ite.getCause();
			              }
			            
			        	//return method.invoke(service, args);
			        }
			 }
	   });
	   return proxyService;
   	 } catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
	}
  /*  //根据引用的Dao，返回它的具体的实现方法
     public <T> T getInstance(Class<T> clazz)
     {
    	try {
    	String infName=clazz.getSimpleName();//取得它的接口名
    	String imlName=properties.getProperty(infName);//取得它的实现方法名
    	return (T) Class.forName(imlName).newInstance();//返回具体的实现的方法；
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}*/
}
