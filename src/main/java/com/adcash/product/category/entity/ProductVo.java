package com.adcash.product.category.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ProductVo {
  private String name;
  @JsonInclude(JsonInclude.Include.NON_NULL)
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
