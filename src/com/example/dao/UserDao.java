package com.example.dao;

import java.sql.Connection;

import com.example.domain.User;

public interface UserDao extends Dao{
    /**
     * ͨ���û��������û�
     * @param username �û���
     * @return ����ҵ��û����򷵻ط�װ���û���Ϣ��bean��û���򷵻�null;
     */
	User findUserByUsername(String username);
    
	/**
	 * ����û���ע�ᣩ
	 * @param user ��װ���û����ݵ�bean
	 */
	void addUser(User user);
    /**
     * ͨ������������û�
     * @param activecode ������
     * @return ����һ����װ�м����û���Ϣ��bean����
     */
	User findUserByActivecode(String activecode);
    /**
     * �޸��û��ļ���״̬��0��ʾδ���1��ʾ�Ѽ��
     * @param id Ҫ�����û���id
     * @param i ������״̬������
     */
	void updateState(int id, int state);
    /**
     * ͨ��idɾ���û���Ϣ
     * @param id �û�id
     */
	void deleteUser(int id);
    /**
     * ͨ���û�������������û�
     * @param username �û���
     * @param password  ����
     * @return ����һ����װ���û���Ϣ��bean����
     */
	User findUserByNameAndPwd(String username, String password);
    /**
     * ͨ���û�id��ѯ�û���Ϣ
     * @param user_id �û�id
     * @return ���ط�װ���û���Ϣ��bean����
     */
	User findUserById(int user_id);
     
}
