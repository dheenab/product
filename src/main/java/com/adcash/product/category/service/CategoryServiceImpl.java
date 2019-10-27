package com.adcash.product.category.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	CategoryRepository categoryRepository;
	@Override
	public List<CategoryVo> getAllCategories() {
		 List<Category> categories= categoryRepository.findAll();
		 List<CategoryVo> categoriesVo = null;
		 if(categories !=null && !categories.isEmpty()) {
		  categoriesVo = categories.stream().
		 map(cat-> new CategoryVo(cat.getName(), cat.getProducts()!=null?
				 cat.getProducts().stream().
				 map(pro->new ProductVo(pro.getName(), null))
				 .collect(Collectors.toList()):null)
				 ).collect(Collectors.toList());
		 }
		 logger.info("Fetched list of categories{}",categoriesVo);
		return categoriesVo;
	}

	@Override
	public boolean updateCategorybyName(String categoryName, String newCategoryName) {
		Optional<Category> categoryAvailable= categoryRepository.findByName(categoryName);
		if(categoryAvailable.isPresent()) {
			 logger.info("updating category{} to newCategoryName {}",categoryName, newCategoryName);
			Category category = categoryAvailable.get();
			category.setName(categoryName);
			categoryRepository.save(category);
		}else {
			throw new NotFoundException(categoryName);
		}
	return true;
	}

	@Override
	public boolean createCategory(String categoryName) {
		Optional<Category> categoryAvailable = categoryRepository.findByName(categoryName);
		if (!categoryAvailable.isPresent()) {
			Category category = new Category();
			category.setName(categoryName);
			categoryRepository.save(category);
			logger.info("Created new category {}",categoryName);
		}
		return true;
	}

	@Override
	public void deleteCategory(String categoryName) {
		Optional<Category> category = categoryRepository.findByName(categoryName);
		if(category.isPresent()) {
		 categoryRepository.delete(category.get());
		logger.info("Deleted category {}",categoryName);
		}else throw new NotFoundException("Category "+ categoryName);		
	}

}
