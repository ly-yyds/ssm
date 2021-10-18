package com.mage.service;

import java.util.List;

import com.mage.po.Notice;

public interface NoticeService {

	// 查询公告集合
	List<Notice> findAll();

	// 通过id查询公告
	Notice findNoticeById(int id);

}
