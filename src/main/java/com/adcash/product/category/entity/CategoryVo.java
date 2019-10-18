package com.adcash.product.category.entity;

import java.util.List;

public class CategoryVo {
 
	private String name;
	
	private List<ProductVo> products;
	
	public CategoryVo(String name, List<ProductVo> products) {
		this.name=name;
		this.products=products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductVo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVo> products) {
		this.products = products;
	}
	
 
}
