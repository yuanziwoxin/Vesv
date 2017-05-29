package com.example.service;

import java.sql.Connection;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.example.dao.UserDao;
import com.example.domain.User;
import com.example.factory.BasicFactory;


public class UserServiceImpl implements UserService{
	private UserDao dao=BasicFactory.getFactory().getDao(UserDao.class);
	public void regist(User user){
      try{

			//1、检验用户是否存在
			
			if(dao.findUserByUsername(user.getUsername())!=null)
			{//如果有该用户，则提示“用户已存在”
				throw new RuntimeException("用户已存在！");
			}else{//如果没有该用户，则注册用户；
				user.setRole("user");
				user.setState(0);
				user.setActivecode(UUID.randomUUID().toString());
				dao.addUser(user);
			}
			//2、发送激活邮件
			Properties prop=new Properties();
			prop.setProperty("mail.transport.protocol","smtp");//发送协议
			prop.setProperty("mail.smtp.host","localhost");//主机名
			prop.setProperty("mail.smtp.auth","true");//是否开启权限控制
			prop.setProperty("mail.debug", "true");//设置为true，则在发送邮件时会打印出邮件内容；
			
			//创建程序到邮箱服务器之间的一次会话
			Session session=Session.getInstance(prop);
			//获取邮箱对象
			Message msg=new  MimeMessage(session);
			msg.setFrom(new InternetAddress("Estore@yuan.com"));
			msg.setRecipient(RecipientType.TO,new InternetAddress(user.getEmail()));
			msg.setSubject(user.getUsername()+"来自Estore的激活邮件");
			msg.setText(user.getUsername()+"您好,这是Estore账户的激活邮件，您的激活码是"+user.getActivecode()+",请复制以下地址至地址栏进行激活操作：http://localhost:8080/Estore/ActiveServlet?activecode="+user.getActivecode());
			
			//找到邮递员
			Transport trans=session.getTransport();
			trans.connect("Estore", "123");//输入用户名密码
			trans.sendMessage(msg,msg.getAllRecipients());
      }catch(Exception e){
    	  e.printStackTrace();
    	  throw new RuntimeException(e);
      }
	}

	public User active(String activecode) {
		
		
		User user=dao.findUserByActivecode(activecode);
		//如果激活码不正确，则不予激活，提示激活码错误；
		if(user==null)
		{
			throw new RuntimeException("对不起！激活码有误！"); 
		}
		//如果用户已激活，则也不予重复激活，提示用户已存在；
		if(user.getState()==1)
		{
			throw new RuntimeException("用户已激活！请直接登录！"); 
		}
		//如果激活码过时，则也不予激活，提示激活码过时，请重新注册用户；
		if(System.currentTimeMillis()-user.getUpdatetime().getTime()>1000*3600*24)
		{  //并要从数据库中删除之前未激活的用户信息；
			dao.deleteUser(user.getId());
			throw new RuntimeException("激活码已过时！请重新注册！");
		}
		user.setState(1);
		dao.updateState(user.getId(),1);//修改激活状态为已激活（1）
		return user;
	}

	public User getUserByNameAndPwd(String username, String password) {
		
		return dao.findUserByNameAndPwd(username,password);
	}

	public boolean VailName(String username) {
		
		return dao.findUserByUsername(username)!=null;
	}
      
}
