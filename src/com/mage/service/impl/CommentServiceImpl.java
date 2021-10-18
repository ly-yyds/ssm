package com.mage.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mage.po.Comment;
import com.mage.po.Notice;
import com.mage.service.CommentService;
import com.mage.util.DBUtil;

public class CommentServiceImpl implements CommentService {

	// 查询当前页留言集合
	@Override
	public List<Comment> findCommentListByPage(Integer page, int pageSize) {
		List<Comment> list = new ArrayList<Comment>();
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_comment order by createTime desc limit ?,?;";
			// new queryRunner
			QueryRunner qr = new QueryRunner();
			// 创建参数数组
			Object[] params = {(page-1)*pageSize,pageSize};
			// 执行查询,得到标签集合
			list = qr.query(conn, sql, new BeanListHandler<>(Comment.class),params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return list;
	}

	// 查询留言总数
	@Override
	public int findCommentAmount() {
		List<Comment> list = new ArrayList<Comment>();
		int tatal = 0;
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from t_comment;";
			// new queryRunner
			QueryRunner qr = new QueryRunner();
			// 执行查询,得到标签集合
			list = qr.query(conn, sql, new BeanListHandler<>(Comment.class));
			tatal = list.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return tatal;
	}

	// 添加留言
	@Override
	public int addComment(Comment comment) {
		int row = 0;
		Connection conn = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 编写sql
			String sql = "insert into t_comment (content,createTime,nickName) values (?,now(),?);";
			// new queryRunner
			QueryRunner qr = new QueryRunner();
			// 创建参数数组
			Object[] params = {comment.getContent(),comment.getNickName()};
			// 执行添加，得到受影响行数
			row = qr.update(conn, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
			DBUtil.close(null, null, conn);
		}
		return row;
	}

}
