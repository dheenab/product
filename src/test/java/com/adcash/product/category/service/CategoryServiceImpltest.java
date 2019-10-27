package com.adcash.product.category.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.adcash.product.category.entity.Category;
import com.adcash.product.category.entity.CategoryVo;
import com.adcash.product.category.entity.exception.NotFoundException;
import com.adcash.product.category.entity.repositories.CategoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImpltest {
	
	@Mock
    CategoryRepository categoryRepository;
	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;
	
	@Test
	public void test_getAllcategories(){
	 Category c = new Category();
	 c.setName("Electronics");
	 List<Category> categories = new ArrayList<>();
	 categories.add(c);
	 Mockito.when(categoryRepository.findAll()).thenReturn(categories);
	 List<CategoryVo> categoriesAll= categoryServiceImpl.getAllCategories();
	 assertEquals(1, categoriesAll.size());
	 assertEquals("Electronics", categoriesAll.get(0).getName());
	}
	
	
	@Test
	public void test_updatecategoryByName(){
	 Category c = new Category();
	 c.setName("Electronics");
	 Mockito.when(categoryRepository.findByName("Electronics")).thenReturn(Optional.of(c));
	 boolean categoriesAll= categoryServiceImpl.updateCategorybyName("Electronics", "Games");
	 assertEquals(true, categoriesAll);
	}
	
	@Test
	public void test_CreatecategoryByName(){
	 Mockito.when(categoryRepository.findByName("Electronics")).thenReturn(Optional.ofNullable(null));
	 boolean categoriesAll= categoryServiceImpl.createCategory("Electronics");
	 assertEquals(true, categoriesAll);
	}
	
	@Test
	public void test_DeletecategoryByName(){
		try {
	 Mockito.when(categoryRepository.findByName("Electronics")).thenReturn(Optional.ofNullable(null));
	 categoryServiceImpl.deleteCategory("Electronics");
		}catch (NotFoundException e) {
			assertEquals("Category Electronics not found",e.getMessage());
		}
	}
	
	

}
