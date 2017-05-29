package com.example.service;

import com.example.annotation.Tran;
import com.example.domain.User;

public interface UserService extends Service{

	/**
	 *����û���ע�ᣩ
	 * @param user һ����װ���û���Ϣ��bean
	 * 
	 * 
	 */
	@Tran
	void regist(User user);
   /**
    * ���ü�������û����м���
    * @param activecode ������
    * @return ��װ�м����û���Ϣ��bean
    */
	User active(String activecode);
	/**
	 * ͨ���û����������¼�û�
	 * @param username �û���
	 * @param password  ����
	 * @return ����һ����װ���û���Ϣ��bean���󣬷�֮����null��
	 */
    User getUserByNameAndPwd(String username, String password);
	boolean VailName(String username);

}
