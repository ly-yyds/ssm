package com.mage.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mage.po.Order;
import com.mage.po.OrderProduct;
import com.mage.service.OrderProductService;
import com.mage.service.OrderService;
import com.mage.util.DBUtil;

public class OrderServiceImpl implements OrderService {
	
	private OrderProductService orderProductService = new OrderProductServiceImpl();

	
	// 通过用户id查询订单集合
	@Override
	public List<Order> findOrderListByUserId(Integer userId) {
		List<Order> orderlist = null;
		Connection conn = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_order where userId = ?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 创建参数数组
			Object[] params = {userId};
			// 执行更新
			orderlist = qr.query(conn, sql, new BeanListHandler<>(Order.class),params);
			for (Order order : orderlist) {
				List<OrderProduct> orderProductList = orderProductService.findOrderProductListByOrderId(order.getId());
				order.setOrderProductList(orderProductList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return orderlist;
	}


	/**
	 * 订单查询
	 */
	@Override
	public List<Order> findOrderListByOrderNo(String orderNo, Integer userId) {
		List<Order> orderlist = null;
		Connection conn = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_order where orderNo like ? and userId = ?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 创建参数数组
			Object[] params = {"%"+orderNo+"%",userId};
			// 执行更新
			orderlist = qr.query(conn, sql, new BeanListHandler<>(Order.class),params);
			for (Order order : orderlist) {
				List<OrderProduct> orderProductList = orderProductService.findOrderProductListByOrderId(order.getId());
				order.setOrderProductList(orderProductList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return orderlist;
	}


	/**
	 * 确认收货
	 */
	@Override
	public int confirmReceive(int orderId) {
		int row = 0;
		Connection conn = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "update t_order set status = 4 where id = ?";
			// new QueryRunner
			QueryRunner qr = new QueryRunner();
			// 创建参数数组
			Object[] params = {orderId};
			// 执行更新
			row = qr.update(conn, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return row;
	}


	// 添加订单
	@Override
	public Integer saveOrder(Order order) {
		Long orderId = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into t_order (cost,createTime,orderNo,status,userId) values (?,now(),?,?,?);";
			QueryRunner qr = new QueryRunner();
			Object[] params = {order.getCost(),order.getOrderNo(),order.getStatus(),order.getUserId()};
			orderId = qr.insert(conn, sql, new ScalarHandler<>(), params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, null, conn);
		}
		return orderId.intValue();
	}

}
