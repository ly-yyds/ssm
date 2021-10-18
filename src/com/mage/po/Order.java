package com.mage.po;

import java.util.Date;
import java.util.List;

public class Order {
	
  	private Integer id; // 主键id
  	private float cost; // 订单总价
  	private Date createTime; // 创建时间
  	private String orderNo; // 订单编号
  	private Integer status; // 订单状态  1 待审核   2 待发货 3 确认收货 4交易完毕
  	private Integer userId; // 用户id
  	
  	private List<OrderProduct> orderProductList;
  	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	

}
