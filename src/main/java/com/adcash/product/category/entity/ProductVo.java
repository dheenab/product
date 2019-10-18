package com.adcash.product.category.entity;

import java.util.List;

public class ProductVo {
  private String name;
  private List<String> categories;
  public ProductVo(String name, List<String> categories) {
	  this.name= name;
	  this.categories= categories;
  }
  public String getName() {
	return name;
  }
  public void setName(String name) {
	this.name = name;
  }
  public List<String> getCategories() {
	return categories;
  }
  public void setCategories(List<String> categories) {
	this.categories = categories;
  }
  
  
  
}
