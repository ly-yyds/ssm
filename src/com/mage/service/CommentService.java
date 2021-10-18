package com.mage.service;

import java.util.List;

import com.mage.po.Comment;

/**
 * 留言service
 * @author Cushier
 *
 */
public interface CommentService {

	// 查询当前页留言集合
	List<Comment> findCommentListByPage(Integer page, int pageSize);

	// 查询留言总数
	int findCommentAmount();

	// 添加留言
	int addComment(Comment comment);

}
