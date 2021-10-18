package com.mage.po;

/**
 * 子类别bean
 * @author Cushier
 *
 */
public class SmallType {
	private Integer id; // 主键id
	private String name; // 名称
	private String remarks; // 备注
	private Integer bigTypeId; // 对应的大类别Id
	public SmallType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SmallType(Integer id, String name, String remarks, Integer bigTypeId) {
		super();
		this.id = id;
		this.name = name;
		this.remarks = remarks;
		this.bigTypeId = bigTypeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getBigTypeId() {
		return bigTypeId;
	}
	public void setBigTypeId(Integer bigTypeId) {
		this.bigTypeId = bigTypeId;
	}
	
	
}
