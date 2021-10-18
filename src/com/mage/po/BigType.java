package com.mage.po;

import java.util.List;

/**
 * 大类别 bean
 * @author Cushier
 *
 */
public class BigType {
	
	private Integer id; // 主键id
	private String name; // 名称
	private String remarks; // 备注
	private List<SmallType> smallTypeList;// 子类别集合

	public BigType() {
		super();
	}

	public BigType(Integer id, String name, String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.remarks = remarks;
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

	public List<SmallType> getSmallTypeList() {
		return smallTypeList;
	}

	public void setSmallTypeList(List<SmallType> smallTypeList) {
		this.smallTypeList = smallTypeList;
	}
	
	
}