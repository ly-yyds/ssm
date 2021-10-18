package com.mage.po;

import java.util.List;

public class ShoppingCart {
	
	private Integer userId;
	private List<ShoppingCartItem> ShoppingCartItems;
	
	public ShoppingCart(Integer userId, List<ShoppingCartItem> shoppingCartItems) {
		super();
		this.userId = userId;
		ShoppingCartItems = shoppingCartItems;
	}
	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<ShoppingCartItem> getShoppingCartItems() {
		return ShoppingCartItems;
	}
	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		ShoppingCartItems = shoppingCartItems;
	}
	
	

}
