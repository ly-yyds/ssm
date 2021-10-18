package com.mage.po;

/**
 * 商品单元bean
 * @author Cushier
 *
 */
public class ShoppingCartItem {
	
	private Product product;
	private Integer count;

	public ShoppingCartItem(Product product, Integer count) {
		super();
		this.product = product;
		this.count = count;
	}
	public ShoppingCartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
