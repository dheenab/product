package com.adcash.product.category.service;

import java.util.List;

import com.adcash.product.category.entity.CategoryVo;
import com.adcash.product.category.entity.ProductVo;
import com.adcash.product.category.entity.exception.NotFoundException;

public interface CategoryService {
	
    List<CategoryVo> getAllCategories();

    
    boolean updateCategorybyName(String categoryName, String newCategoryName);
  
    boolean createCategory(String categoryName);
    
    void deleteCategory(String categoryName) throws NotFoundException;
      
}
