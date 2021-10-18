package com.mage.service;

import java.util.List;

import com.mage.po.OrderProduct;

public interface OrderProductService {

	// 通过订单id 查询 orderProduct集合
	List<OrderProduct> findOrderProductListByOrderId(Integer orderId);

	// 添加中间表
	void saveOrderProduct(OrderProduct orderProduct);

}
