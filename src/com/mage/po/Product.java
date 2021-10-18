package com.mage.po;

import java.util.Date;

/**
 * 商品bean
 * @author Cushier
 *
 */
public class Product {

	private Integer id; // 主键id
	private String description; // 商品描述
	private Integer hot; // 是否热门  1是 0不是
	private Date datetime; // 热门截至时间
	private String name; // 商品名称
	private Integer price; // 商品价格
	private String proPic; // 商品图片路径
	private Integer specialPrice; // 是否特价  1是 0不是
	private Date specialPriceTime; // 特价截至时间
	private Integer stock; // 库存
	private Integer bigTypeId; // 外键，大类别id
	private Integer smallTypeId; // 外键，小类别id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	public Integer getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(Integer specialPrice) {
		this.specialPrice = specialPrice;
	}
	public Date getSpecialPriceTime() {
		return specialPriceTime;
	}
	public void setSpecialPriceTime(Date specialPriceTime) {
		this.specialPriceTime = specialPriceTime;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getBigTypeId() {
		return bigTypeId;
	}
	public void setBigTypeId(Integer bigTypeId) {
		this.bigTypeId = bigTypeId;
	}
	public Integer getSmallTypeId() {
		return smallTypeId;
	}
	public void setSmallTypeId(Integer smallTypeId) {
		this.smallTypeId = smallTypeId;
	}

}
