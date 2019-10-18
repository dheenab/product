package com.adcash.product.category.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adcash.product.category.entity.Category;
import com.adcash.product.category.entity.CategoryVo;
import com.adcash.product.category.entity.Product;
import com.adcash.product.category.entity.ProductVo;
import com.adcash.product.category.entity.repositories.CategoryRepository;
import com.adcash.product.category.entity.repositories.ProductRepository;
@Transactional
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
	ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
	@Override
	public List<ProductVo> getProductsByCategory(String categoryName) {
		List<Product> products = productRepository.findProductsByCategory(categoryName);
		List<ProductVo> productsVo = new ArrayList<>();
		if (!products.isEmpty()) {
			productsVo = products.stream().map(product -> new ProductVo(product.getName(), null))
					.collect(Collectors.toList());
		}
		return productsVo;
	}

	@Override
	public ProductVo addProductToCategory(ProductVo productVo) {
		Category category = null;
		Product product = null;
		ProductVo productVoResponse = null;
		if (productVo != null && productVo.getCategories() != null) {
			if (productVo != null && productVo.getName() != null && !productVo.getName().isEmpty()) {
				product = productRepository.findByName(productVo.getName());
			}
			if (product == null) {
				product = new Product();
				product.setName(productVo.getName());
			}

			for (String categoryName : productVo.getCategories()) {
				if (!product.getCategories().contains(category)) {
					if (categoryName != null && !categoryName.isEmpty()) {
						category = categoryRepository.findByName(categoryName);
					}
					if (category == null) {
						category = new Category();
						category.setName(categoryName);
						categoryRepository.save(category);
					}
				}
				product.getCategories().add(category);

			}
			productRepository.save(product);
			productVoResponse = new ProductVo(product.getName(),
					product.getCategories().stream().map(cat -> cat.getName()).collect(Collectors.toList()));
		}
		return productVoResponse;
	}

	@Override
	public void updateProduct(String productName, String newProductName) {
		Product product = productRepository.findByName(productName);
		if(product != null) {
		 product.setName(newProductName);
		 productRepository.save(product);
		}
		
	}

	@Override
	public ProductVo createProduct(String productName) {
		Product product= new Product();
		product.setName(productName);
		productRepository.save(product);
		ProductVo productVo = new ProductVo(productName, null);
		return productVo;
	}

	@Override
	public void deleteProduct(String productName) {
		Product product = productRepository.findByName(productName);
		productRepository.delete(product);		
	}

}
