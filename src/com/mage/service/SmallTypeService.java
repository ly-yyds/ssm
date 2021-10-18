package com.mage.service;

import java.util.List;

import com.mage.po.SmallType;

public interface SmallTypeService {

	// 通过大类别id查询子类别集合
	List<SmallType> findByBigTypeId(int int1);

}
