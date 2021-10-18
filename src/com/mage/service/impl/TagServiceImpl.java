package com.mage.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mage.po.Tag;
import com.mage.service.TagService;
import com.mage.util.DBUtil;

public class TagServiceImpl implements TagService {

	// 查询所有标签集合
	@Override
	public List<Tag> findAll() {
		List<Tag> list = new ArrayList<>();
		Connection conn = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_tag";
			// new queryRunner
			QueryRunner qr = new QueryRunner();
			// 执行查询,得到标签集合
			list = qr.query(conn, sql, new BeanListHandler<>(Tag.class));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return list;
	}

}
