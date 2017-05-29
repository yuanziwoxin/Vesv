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

			//1�������û��Ƿ����
			
			if(dao.findUserByUsername(user.getUsername())!=null)
			{//����и��û�������ʾ���û��Ѵ��ڡ�
				throw new RuntimeException("�û��Ѵ��ڣ�");
			}else{//���û�и��û�����ע���û���
				user.setRole("user");
				user.setState(0);
				user.setActivecode(UUID.randomUUID().toString());
				dao.addUser(user);
			}
			//2�����ͼ����ʼ�
			Properties prop=new Properties();
			prop.setProperty("mail.transport.protocol","smtp");//����Э��
			prop.setProperty("mail.smtp.host","localhost");//������
			prop.setProperty("mail.smtp.auth","true");//�Ƿ���Ȩ�޿���
			prop.setProperty("mail.debug", "true");//����Ϊtrue�����ڷ����ʼ�ʱ���ӡ���ʼ����ݣ�
			
			//�����������������֮���һ�λỰ
			Session session=Session.getInstance(prop);
			//��ȡ�������
			Message msg=new  MimeMessage(session);
			msg.setFrom(new InternetAddress("Estore@yuan.com"));
			msg.setRecipient(RecipientType.TO,new InternetAddress(user.getEmail()));
			msg.setSubject(user.getUsername()+"����Estore�ļ����ʼ�");
			msg.setText(user.getUsername()+"����,����Estore�˻��ļ����ʼ������ļ�������"+user.getActivecode()+",�븴�����µ�ַ����ַ�����м��������http://localhost:8080/Estore/ActiveServlet?activecode="+user.getActivecode());
			
			//�ҵ��ʵ�Ա
			Transport trans=session.getTransport();
			trans.connect("Estore", "123");//�����û�������
			trans.sendMessage(msg,msg.getAllRecipients());
      }catch(Exception e){
    	  e.printStackTrace();
    	  throw new RuntimeException(e);
      }
	}

	public User active(String activecode) {
		
		
		User user=dao.findUserByActivecode(activecode);
		//��������벻��ȷ�����輤���ʾ���������
		if(user==null)
		{
			throw new RuntimeException("�Բ��𣡼���������"); 
		}
		//����û��Ѽ����Ҳ�����ظ������ʾ�û��Ѵ��ڣ�
		if(user.getState()==1)
		{
			throw new RuntimeException("�û��Ѽ����ֱ�ӵ�¼��"); 
		}
		//����������ʱ����Ҳ���輤���ʾ�������ʱ��������ע���û���
		if(System.currentTimeMillis()-user.getUpdatetime().getTime()>1000*3600*24)
		{  //��Ҫ�����ݿ���ɾ��֮ǰδ������û���Ϣ��
			dao.deleteUser(user.getId());
			throw new RuntimeException("�������ѹ�ʱ��������ע�ᣡ");
		}
		user.setState(1);
		dao.updateState(user.getId(),1);//�޸ļ���״̬Ϊ�Ѽ��1��
		return user;
	}

	public User getUserByNameAndPwd(String username, String password) {
		
		return dao.findUserByNameAndPwd(username,password);
	}

	public boolean VailName(String username) {
		
		return dao.findUserByUsername(username)!=null;
	}
      
}
