package com.mage.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mage.po.SmallType;
import com.mage.service.SmallTypeService;
import com.mage.util.DBUtil;

public class SmallTypeServiceImpl implements SmallTypeService {

	// 通过大类别id查询子类别集合
	@Override
	public List<SmallType> findByBigTypeId(int bigTypeId) {
		List<SmallType> list = new ArrayList<SmallType>();
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_smalltype where bigTypeId = ?";
			// new queryRunner
			QueryRunner qr = new QueryRunner();
			// 新建参数数组
			Object[] prams = {bigTypeId};
			// 执行查询,得到标签集合
			list = qr.query(conn, sql, new BeanListHandler<>(SmallType.class),prams);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return list;
	}

}
