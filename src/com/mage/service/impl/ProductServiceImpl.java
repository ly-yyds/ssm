package com.mage.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mage.po.Notice;
import com.mage.po.Product;
import com.mage.service.ProductService;
import com.mage.util.DBUtil;

public class ProductServiceImpl implements ProductService {

	// 查询今日特价商品 
	@Override
	public List<Product> findSpecialProduct() {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_product where specialPrice = 1 and specialPriceTime >= now() order by specialPriceTime;";
			// new queryRunner
			QueryRunner qr = new QueryRunner();
			// 执行查询,得到标签集合
			list = qr.query(conn, sql, new BeanListHandler<>(Product.class));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return list;
	}

	// 查询热卖推荐商品
	@Override
	public List<Product> findhotProduct() {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_product where hot = 1 and hotTime >= now() order by hotTime;";
			// new queryRunner
			QueryRunner qr = new QueryRunner();
			// 执行查询,得到标签集合
			list = qr.query(conn, sql, new BeanListHandler<>(Product.class));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return list;
	}

	// 通过id查询商品详情
	@Override
	public Product findProductById(int id) {
		Product product = null;
		Connection conn = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_product where id = ?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 新建参数数组
			Object[] params = {id};
			// 执行查询
			product = qr.query(conn, sql, new BeanHandler<>(Product.class),params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return product;
	}

	// 查询当前页商品集合通过小类别id
	@Override
	public List<Product> findProductListBySmallType(Integer page, int pageSize, Integer smallTypeId) {
		List<Product> list = null;
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_product where smallTypeId = ? order by hotTime desc limit ?,?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 参数
			Object[] params = {smallTypeId,(page-1)*pageSize,pageSize};
			// 执行查询
			list = qr.query(conn, sql, new BeanListHandler<>(Product.class), params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return list;
	}

	// 查询商品总数通过小类别id
	@Override
	public int findProductAmountBySmallType(Integer smallTypeId) {
		int total = 0;
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_product where smallTypeId = ?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 参数
			Object[] params = {smallTypeId};
			// 执行查询
			List<Product> list = qr.query(conn, sql, new BeanListHandler<>(Product.class), params);
			total = list.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return total;
	}

	// 模糊查询当前页商品集合通过名称
	@Override
	public List<Product> findProductListByName(Integer page, int pageSize, String keyword) {
		List<Product> list = null;
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_product where name like ? order by hotTime desc limit ?,?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 参数
			Object[] params = {"%"+keyword+"%",(page-1)*pageSize,pageSize};
			// 执行查询
			list = qr.query(conn, sql, new BeanListHandler<>(Product.class), params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return list;
	}

	// 模糊查询商品总数通过名称
	@Override
	public int findProductAmountByName(String keyword) {
		int total = 0;
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_product where name like ?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 参数
			Object[] params = {"%"+keyword+"%"};
			// 执行查询
			List<Product> list = qr.query(conn, sql, new BeanListHandler<>(Product.class), params);
			total = list.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return total;
	}

	// 查询当前页商品集合通过大类别id
	@Override
	public List<Product> findProductListByBigType(Integer page, int pageSize, Integer bigTypeId) {
		List<Product> list = null;
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_product where bigTypeId = ? order by hotTime desc limit ?,?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 参数
			Object[] params = {bigTypeId,(page-1)*pageSize,pageSize};
			// 执行查询
			list = qr.query(conn, sql, new BeanListHandler<>(Product.class), params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return list;
	}

	// 查询商品总数通过大类别id
	@Override
	public int findProductAmountByBigType(Integer bigTypeId) {
		int total = 0;
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_product where bigTypeId = ?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 参数
			Object[] params = {bigTypeId};
			// 执行查询
			List<Product> list = qr.query(conn, sql, new BeanListHandler<>(Product.class), params);
			total = list.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return total;
	}

}
