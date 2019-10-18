package com.adcash.product.category.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adcash.product.category.entity.Category;
import com.adcash.product.category.entity.CategoryVo;
import com.adcash.product.category.entity.ProductVo;
import com.adcash.product.category.entity.exception.NotFoundException;
import com.adcash.product.category.entity.repositories.CategoryRepository;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	@Override
	public List<CategoryVo> getAllCategories() {
		 List<Category> categories= categoryRepository.findAll();
		 List<CategoryVo> categoriesVo = categories.stream().
		 map(cat-> new CategoryVo(cat.getName(), 
				 cat.getProducts().stream().
				 map(pro->new ProductVo(pro.getName(), null))
				 .collect(Collectors.toList()))
				 ).collect(Collectors.toList());
		return categoriesVo;
	}

	@Override
	public boolean updateCategorybyName(String categoryName, String newCategoryName) {
		Category category= categoryRepository.findByName(categoryName);
		if(category != null) {
			category.setName(categoryName);
			categoryRepository.save(category);
		}else {
			throw new NotFoundException("Category is Null");
		}
	return true;
	}

	@Override
	public boolean createCategory(String categoryName) {
		Category category = new Category();
		category.setName(categoryName);
		categoryRepository.save(category);
		return true;
	}

	@Override
	public void deleteCategory(String categoryName) {
		Category category = categoryRepository.findByName(categoryName);
		if(category !=null)
		 categoryRepository.delete(category);
	}

}
