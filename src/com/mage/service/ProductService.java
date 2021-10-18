package com.mage.service;

import java.util.List;

import com.mage.po.Product;

public interface ProductService {

	// 查询今日特价商品
	List<Product> findSpecialProduct();

	// 查询热卖推荐商品
	List<Product> findhotProduct();

	// 查询商品通过id
	Product findProductById(int id);

	// 查询当前页商品集合通过小类别id
	List<Product> findProductListBySmallType(Integer page, int pageSize, Integer smallTypeId);

	// 查询商品总数通过小类别id
	int findProductAmountBySmallType(Integer smallTypeId);

	// 模糊查询当前页商品集合通过名称
	List<Product> findProductListByName(Integer page, int pageSize, String keyword);

	// 模糊查询商品总数通过名称
	int findProductAmountByName(String keyword);

	// 查询当前页商品集合通过大类别id
	List<Product> findProductListByBigType(Integer page, int pageSize, Integer bigTypeId);

	// 查询商品总数通过大类别id
	int findProductAmountByBigType(Integer bigTypeId);

}
