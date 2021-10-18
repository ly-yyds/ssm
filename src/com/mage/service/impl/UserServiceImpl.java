package com.mage.service.impl;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mage.po.User;
import com.mage.service.UserService;
import com.mage.util.DBUtil;

public class UserServiceImpl implements UserService {

	// 通过用户名查询用户对象
	@Override
	public User findUserByName(String userName) {
		User user = null;
		Connection conn = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_user where userName=?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 创建参数数组
			Object[] params = {userName};
			// 执行查询
			user = qr.query(conn, sql, new BeanHandler<>(User.class),params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return user;
	}

	// 注册，添加用户
	@Override
	public int addUser(User user) {
		int row = 0;
		Connection conn = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "insert into t_user (address,birthday,dentityCode,email,mobile,password,sex,status,trueName,userName) values (?,?,?,?,?,?,?,?,?,?);";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 创建参数数组
			Object[] params = {user.getAddress(),user.getBirthday(),user.getDentityCode(),user.getEmail(),user.getMobile(),user.getPassword(),user.getSex(),user.getStatus(),user.getTrueName(),user.getUserName()};
			// 执行更新
			row = qr.update(conn, sql ,params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return row;
	}

	// 更新用户
	@Override
	public int updateUser(User u) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_user set address = ?,birthday = ?,dentityCode = ?,email = ?,mobile = ?,password = ?,sex = ?,trueName = ? where id = ?;";
			QueryRunner qr = new QueryRunner();
			Object[] params = {u.getAddress(),u.getBirthday(),u.getDentityCode(),u.getEmail(),u.getMobile(),u.getPassword(),u.getSex(),u.getTrueName(),u.getId()};
			row = qr.update(conn, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, null, conn);
		}
		return row;
	}

}
