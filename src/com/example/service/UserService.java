package com.example.service;

import com.example.annotation.Tran;
import com.example.domain.User;

public interface UserService extends Service{

	/**
	 *添加用户（注册）
	 * @param user 一个封装有用户信息的bean
	 * 
	 * 
	 */
	@Tran
	void regist(User user);
   /**
    * 利用激活码对用户进行激活
    * @param activecode 激活码
    * @return 封装有激活用户信息的bean
    */
	User active(String activecode);
	/**
	 * 通过用户名和密码登录用户
	 * @param username 用户名
	 * @param password  密码
	 * @return 返回一个封装有用户信息的bean对象，反之返回null；
	 */
    User getUserByNameAndPwd(String username, String password);
	boolean VailName(String username);

}
