package com.mage.po;

import java.util.Date;

/**
 * 新闻bean
 * @author Cushier
 *
 */
public class News {
	
	private Integer id;// 主键id
	private String  content; // 新闻内容
	private Date createTime; // 创建时间
	private String title; // 新闻标题
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
