package com.mage.service;

import com.mage.po.User;

public interface UserService {

	// 通过用户名查询用户对象
	User findUserByName(String userName);

	// 注册，添加用户
	int addUser(User user);

	// 更新用户
	int updateUser(User u);

}
