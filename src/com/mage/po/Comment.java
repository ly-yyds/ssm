package com.mage.po;

import java.util.Date;

/**
 * 留言 bean
 * @author Cushier
 *
 */
public class Comment {
	private Integer id; // 主键id
	private String content; // 留言内容
	private Date createTime; // 创建时间
	private String nickName; // 昵称
	private String replyContent; // 回复内容
	private Date replyTime; // 回复时间

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String content, String nickName) {
		super();
		this.content = content;
		this.nickName = nickName;
	}
	
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
}
