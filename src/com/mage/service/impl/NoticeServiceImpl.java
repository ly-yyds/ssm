package com.mage.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mage.po.Notice;
import com.mage.service.NoticeService;
import com.mage.util.DBUtil;

public class NoticeServiceImpl implements NoticeService {

	// 查询公告集合
	@Override
	public List<Notice> findAll() {
		List<Notice> list = new ArrayList<Notice>();
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_notice order by createTime desc";
			// new queryRunner
			QueryRunner qr = new QueryRunner();
			// 执行查询,得到标签集合
			list = qr.query(conn, sql, new BeanListHandler<>(Notice.class));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return list;
	}

	// 通过id查询公告
	@Override
	public Notice findNoticeById(int id) {
		Notice notice = null;
		Connection conn = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_notice where id = ?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 新建参数数组
			Object[] params = {id};
			// 执行查询
			notice = qr.query(conn, sql, new BeanHandler<>(Notice.class),params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return notice;
	}

}
