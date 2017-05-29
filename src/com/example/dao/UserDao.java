package com.example.dao;

import java.sql.Connection;

import com.example.domain.User;

public interface UserDao extends Dao{
    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 如果找到用户，则返回封装有用户信息的bean，没有则返回null;
     */
	User findUserByUsername(String username);
    
	/**
	 * 添加用户（注册）
	 * @param user 封装有用户数据的bean
	 */
	void addUser(User user);
    /**
     * 通过激活码查找用户
     * @param activecode 激活码
     * @return 返回一个封装有激活用户信息的bean对象
     */
	User findUserByActivecode(String activecode);
    /**
     * 修改用户的激活状态（0表示未激活，1表示已激活）
     * @param id 要激活用户的id
     * @param i 代表激活状态的数字
     */
	void updateState(int id, int state);
    /**
     * 通过id删除用户信息
     * @param id 用户id
     */
	void deleteUser(int id);
    /**
     * 通过用户名和密码查找用户
     * @param username 用户名
     * @param password  密码
     * @return 返回一个封装有用户信息的bean对象
     */
	User findUserByNameAndPwd(String username, String password);
    /**
     * 通过用户id查询用户信息
     * @param user_id 用户id
     * @return 返回封装有用户信息的bean对象
     */
	User findUserById(int user_id);
     
}
