package com.mage.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mage.po.News;
import com.mage.po.Notice;
import com.mage.service.NewsService;
import com.mage.util.DBUtil;

public class NewsServiceImpl implements NewsService {

	// 查询新闻集合
	@Override
	public List<News> findAll() {
		List<News> list = new ArrayList<News>();
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_news order by createTime desc";
			// new queryRunner
			QueryRunner qr = new QueryRunner();
			// 执行查询,得到标签集合
			list = qr.query(conn, sql, new BeanListHandler<>(News.class));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return list;
	}
	
	// 通过id查询新闻
	@Override
	public News findNewsById(int id) {
		News news = null;
		Connection conn = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_news where id = ?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 新建参数数组
			Object[] params = {id};
			// 执行查询
			news = qr.query(conn, sql, new BeanHandler<>(News.class),params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return news;
	}

}
