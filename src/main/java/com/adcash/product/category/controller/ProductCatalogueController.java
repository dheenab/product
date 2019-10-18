package com.adcash.product.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adcash.product.category.entity.CategoryVo;
import com.adcash.product.category.entity.ProductVo;
import com.adcash.product.category.entity.ResponseStatus;
import com.adcash.product.category.entity.repositories.CategoryRepository;
import com.adcash.product.category.service.CategoryService;
import com.adcash.product.category.service.ProductService;

@RestController
@RequestMapping(path = "/services")
public class ProductCatalogueController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/categories", consumes = "application/json", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},method=RequestMethod.GET)
	public ResponseEntity<ResponseStatus> getAllCategories() {
		List<CategoryVo> categoryResponse = categoryService.getAllCategories();
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatus("Success");
		responseStatus.setData(categoryResponse);
		return new ResponseEntity<ResponseStatus>(responseStatus, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/categories/{categoryName}/products", consumes = "application/json", produces = {
			"application/json", "application/xml" },method=RequestMethod.GET)
	public ResponseEntity<ResponseStatus> getAllProducts(
			@PathVariable("categoryName") String categoryName) {
		List<ProductVo> productsResponse = productService.getProductsByCategory(categoryName);
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatus("Success");
		responseStatus.setData(productsResponse);
		return new ResponseEntity<ResponseStatus>(responseStatus, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/categories/create", consumes = "application/json", produces = {
			"application/json", "application/xml" },method=RequestMethod.POST)
	public ResponseEntity<ResponseStatus> createCategory(
			@RequestBody CategoryVo categoryVo) {
		boolean isCategoryCreated = categoryService.createCategory(categoryVo.getName());
		ResponseStatus responseStatus = new ResponseStatus();
		if(isCategoryCreated) {
		responseStatus.setStatus("Success");
		responseStatus.setData("Category" +categoryVo.getName()+ "created successfully");
		}else {
		responseStatus.setStatus("Failure");
		}
		return new ResponseEntity<ResponseStatus>(responseStatus,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/categories/update/{categoryName}", consumes = "application/json", produces = {
			"application/json", "application/xml" },method=RequestMethod.PUT)
	public ResponseEntity<ResponseStatus> updateCategory(
			@PathVariable("categoryName") String categoryName,@RequestParam("newCategoryName") String newCategoryName) {
		boolean isCategoryCreated = categoryService.updateCategorybyName(categoryName, newCategoryName);
		ResponseStatus responseStatus = new ResponseStatus();
		if(isCategoryCreated) {
		responseStatus.setStatus("Success");
		responseStatus.setData("Category" +categoryName+ "created successfully");
		}else {
		responseStatus.setStatus("Failure");
		}
		return new ResponseEntity<ResponseStatus>(responseStatus,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/categories/delete/{categoryName}", consumes = "application/json", produces = {
			"application/json", "application/xml" },method=RequestMethod.DELETE)
	public ResponseEntity<ResponseStatus> deleteCategory(
			@PathVariable("categoryName") String categoryName) {
	       categoryService.deleteCategory(categoryName);
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatus("Success");
		responseStatus.setData("Category" +categoryName+ "deleted successfully");
		return new ResponseEntity<ResponseStatus>(responseStatus,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/product/create/", consumes = "application/json", produces = {
			"application/json", "application/xml" },method=RequestMethod.POST)
	public ResponseEntity<ResponseStatus> createProduct(@RequestBody ProductVo productVo) {
	     ProductVo product = productService.createProduct(productVo.getName());
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatus("Success");
		responseStatus.setData("Product" +product.getName()+ "deleted successfully");
		return new ResponseEntity<ResponseStatus>(responseStatus,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/product/add/", consumes = "application/json", produces = {
			"application/json", "application/xml" },method=RequestMethod.PATCH)
	public ResponseEntity<ResponseStatus> AddProductToCategory(@RequestBody ProductVo productVo) {
	     ProductVo product = productService.addProductToCategory(productVo);
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatus("Success");
		responseStatus.setData(productVo);
		responseStatus.setData("Product" +product.getName()+ "added successfully");
		return new ResponseEntity<ResponseStatus>(responseStatus,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/product/delete/{productName}", consumes = "application/json", produces = {
			"application/json", "application/xml" },method=RequestMethod.DELETE)
	public ResponseEntity<ResponseStatus> deleteProduct(@PathVariable String productName) {
	    productService.deleteProduct(productName);
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatus("Success");
		responseStatus.setData("Product" +productName+ "deleted successfully");
		return new ResponseEntity<ResponseStatus>(responseStatus,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/product/update/{productName}", consumes = "application/json", produces = {
			"application/json", "application/xml" },method=RequestMethod.PUT)
	public ResponseEntity<ResponseStatus> updateProduct(
			@PathVariable("productName") String productName,@RequestParam("newProductName") String newProductName) {
	    productService.updateProduct(productName, newProductName);
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatus("Success");
		responseStatus.setData("Product" +productName+ "deleted successfully");
		return new ResponseEntity<ResponseStatus>(responseStatus,HttpStatus.OK);
	}

}
