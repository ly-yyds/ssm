package com.mage.service;

import java.util.List;

import com.mage.po.Order;

public interface OrderService {

	// 通过用户id查询订单集合
	List<Order> findOrderListByUserId(Integer userId);

	// 订单查询
	List<Order> findOrderListByOrderNo(String orderNo, Integer userId);

	// 确认收货
	int confirmReceive(int orderId);

	// 添加订单
	Integer saveOrder(Order order);

}
