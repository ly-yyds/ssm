package com.mage.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mage.po.BigType;
import com.mage.po.SmallType;
import com.mage.service.BigTypeService;
import com.mage.service.SmallTypeService;
import com.mage.util.DBUtil;

public class BigTypeServiceImpl implements BigTypeService {
	
	private SmallTypeService smallTypeService = new SmallTypeServiceImpl();

	// 查询大类别集合
	@Override
	public List<BigType> findAll() {
		List<BigType> list = new ArrayList<BigType>();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet res = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_bigtype";
			// 预编译
			sta = conn.prepareStatement(sql);
			// 执行查询，返回结果集
			res = sta.executeQuery();
			// 分析结果集
			while(res.next()){
				BigType bigType = new BigType(res.getInt(1),res.getString(2),res.getString(3));
				// 查询子类别
				List<SmallType> smallTypeList = smallTypeService.findByBigTypeId(res.getInt(1));
				bigType.setSmallTypeList(smallTypeList);
				list.add(bigType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(res, sta, conn);
		}
		return list;
	}

}
