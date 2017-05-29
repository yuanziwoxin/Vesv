package com.example.factory;
/**
 * 1.��̬��������
 * 2.��Service��Dao���з��룻
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
  //�������õ�Dao���������ľ����ʵ�ַ���
    public <T extends Dao> T getDao(Class<T> clazz)
    {
   	try {
   	String infName=clazz.getSimpleName();//ȡ�����Ľӿ���
   	String imlName=properties.getProperty(infName);//ȡ������ʵ�ַ�����
   	return (T) Class.forName(imlName).newInstance();//���ؾ����ʵ�ֵķ�����
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
    
    //�������õ�Service���������ľ����ʵ�ַ���
    @SuppressWarnings("unchecked")
	public <T extends Service> T getService(Class<T> clazz)
    {
   	try {
   //���������ļ����������service
   	String infName=clazz.getSimpleName();//ȡ�����Ľӿ���
   	String imlName=properties.getProperty(infName);//ȡ������ʵ�ַ�����
   	final T service=(T) Class.forName(imlName).newInstance();//���ؾ����ʵ�ֵķ�����
	//Ϊ��ʵ��AOP(����������)������service����ʹ�ÿ��Ը���ע����service����֮ǰ��֮�����ִ��һЩ���� ���磺���������¼��־��ϸ���ȿ��ƣ�
   	T proxyService=(T) Proxy.newProxyInstance(service.getClass().getClassLoader(),service.getClass().getInterfaces(),new InvocationHandler() {
		//����ע������������
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			        if(method.isAnnotationPresent(Tran.class))
			        {//�������ǰ����ΪTran��ע���ǣ���ʹ������
			        	  try{
						         TransactionManager.StartTran();//���������ڵ���service����֮ǰ��
								 
						         Object obj=method.invoke(service, args);//����ִ�еķ���
								 
						         TransactionManager.commit();//�ύ������service��������֮��
								 return obj;
						        }catch(InvocationTargetException e){//���Եײ���쳣
						        	TransactionManager.rollback();//�ع����񣨷����쳣��
						        	throw new RuntimeException(e.getTargetException());//���Եײ���쳣���������ʾ����method�İ�װ��service�����׳����쳣
						        }catch(Exception e){
						        	TransactionManager.rollback();//�ع����񣨷����쳣��
						        	throw new RuntimeException(e);
						        }finally{
						        	TransactionManager.release();//�ͷ���Դ��
						        }
			        }else{//û��ע�⣬��ʹ������ֱ��ִ�з�����
			            //�޸�
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
  /*  //�������õ�Dao���������ľ����ʵ�ַ���
     public <T> T getInstance(Class<T> clazz)
     {
    	try {
    	String infName=clazz.getSimpleName();//ȡ�����Ľӿ���
    	String imlName=properties.getProperty(infName);//ȡ������ʵ�ַ�����
    	return (T) Class.forName(imlName).newInstance();//���ؾ����ʵ�ֵķ�����
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}*/
}
