package com.mage.service;

import java.util.List;

import com.mage.po.News;
import com.mage.po.Notice;

public interface NewsService {

	// 查询新闻集合
	List<News> findAll();
	
	// 通过id查询新闻
	News findNewsById(int id);

}
