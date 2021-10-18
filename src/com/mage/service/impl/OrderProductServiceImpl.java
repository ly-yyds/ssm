package com.mage.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mage.po.Order;
import com.mage.po.OrderProduct;
import com.mage.po.Product;
import com.mage.service.OrderProductService;
import com.mage.service.ProductService;
import com.mage.util.DBUtil;

public class OrderProductServiceImpl implements OrderProductService {
	
	private ProductService productService = new ProductServiceImpl();

	// 通过订单id 查询 orderProduct集合
	@Override
	public List<OrderProduct> findOrderProductListByOrderId(Integer orderId) {
		List<OrderProduct> orderProductlist = null;
		Connection conn = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_order_product where orderId = ?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 创建参数数组
			Object[] params = {orderId};
			// 执行更新
			orderProductlist = qr.query(conn, sql, new BeanListHandler<>(OrderProduct.class),params);
			for (OrderProduct orderProduct : orderProductlist) {
				Product product = productService.findProductById(orderProduct.getProductId());
				orderProduct.setProduct(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return orderProductlist;
	}

	@Override
	public void saveOrderProduct(OrderProduct orderProduct) {
		Connection conn = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "insert into t_order_product (num,orderId,productId) values (?,?,?);";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 创建参数数组
			Object[] params = {orderProduct.getNum(),orderProduct.getOrderId(),orderProduct.getProductId()};
			// 执行更新
			qr.update(conn, sql ,params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
	}

}
