package com.adcash.product.category.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.adcash.product.category.entity.Category;
import com.adcash.product.category.entity.Product;
import com.adcash.product.category.entity.ProductVo;
import com.adcash.product.category.entity.repositories.CategoryRepository;
import com.adcash.product.category.entity.repositories.ProductRepository;
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
	
	@Mock
	ProductRepository productRepository;
	@Mock
    CategoryRepository categoryRepository;
	@InjectMocks
	ProductServiceImpl productServiceImpl;


	@Test
	public void test_getProductsByCategory() {
	Product p = new Product();
	p.setName("toys");
	Category c = new Category();
	c.setName("Electronics");
	Set<Category> categories = new HashSet<>();
	categories.add(c);
	p.setCategories(new HashSet<>(categories));
	List<Product> products = new ArrayList<>();
	products.add(p);
	 Mockito.when(productRepository.findProductsByCategory("Electronics")).thenReturn(Arrays.asList(p));
	 List<ProductVo> productVo= productServiceImpl.getProductsByCategory("Electronics");
	 assertEquals(1, productVo.size());
	 
	}
	
	@Test
	public void test_getaddProductToCategory() {
	
    ProductVo productVo= new ProductVo("toys", Arrays.asList("Electronics"));
    Product p = new Product();
	p.setName("toys");
	Mockito.when(productRepository.findByName("toys")).thenReturn(Optional.of(p));
	Category c = new Category();
	c.setName("Electronics");
	Mockito.when(categoryRepository.findByName("Electronics")).thenReturn(Optional.of(c));
	 ProductVo productVoOutput= productServiceImpl.addProductToCategory(productVo);
	 assertEquals("toys", productVoOutput.getName());
	 assertEquals("Electronics", productVoOutput.getCategories().get(0));

	 
	}
	
	@Test
	public void test_getaddProductToCategory_Add_newCategory() {
	
    ProductVo productVo= new ProductVo("toys", Arrays.asList("Electronics"));
    Product p = new Product();
	p.setName("toys");
	Mockito.when(productRepository.findByName("toys")).thenReturn(Optional.of(p));
	Category c = new Category();
	c.setName("Electronics");
	Mockito.when(categoryRepository.findByName("Electronics")).thenReturn(Optional.ofNullable(null));
	 ProductVo productVoOutput= productServiceImpl.addProductToCategory(productVo);
	 assertEquals("toys", productVoOutput.getName());
	 assertEquals("Electronics", productVoOutput.getCategories().get(0));
     Mockito.verify(categoryRepository);
	 
	}
	
	@Test
	public void test_updateProduct() {
    Product p = new Product();
	p.setName("toys");
	Mockito.when(productRepository.findByName("toys")).thenReturn(Optional.of(p));
	productServiceImpl.updateProduct("toys", "toothbrush");	 
	}
	
	@Test
	public void test_createProduct() {
    Product p = new Product();
	p.setName("toys");
	Mockito.when(productRepository.findByName("toys")).thenReturn(Optional.of(p));
	ProductVo product = productServiceImpl.createProduct("toys");	
	assertEquals("toys",product.getName());
	}
	
	@Test
	public void test_deleteProduct() {
    Product p = new Product();
	p.setName("toys");
	Mockito.when(productRepository.findByName("toys")).thenReturn(Optional.of(p));
    productServiceImpl.deleteProduct("toys");	
	}

}
