package com.mage.po;

/**
 * 标签bean
 * @author Cushier
 *
 */
public class Tag {
	private Integer id; // 主键id
	private String name; // 名称
	private String url; // 地址

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Tag(Integer id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}