package com.adcash.product.category.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adcash.product.category.entity.Category;
import com.adcash.product.category.entity.Product;
import com.adcash.product.category.entity.ProductVo;
import com.adcash.product.category.entity.exception.NotFoundException;
import com.adcash.product.category.entity.repositories.CategoryRepository;
import com.adcash.product.category.entity.repositories.ProductRepository;
@Transactional
@Service
public class ProductServiceImpl implements ProductService{
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
	ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
	@Override
	public List<ProductVo> getProductsByCategory(String categoryName) {
		List<Product> products = productRepository.findProductsByCategory(categoryName);
		List<ProductVo> productsVo = new ArrayList<>();
		if (!products.isEmpty()) {
			productsVo = products.stream().map(product -> new ProductVo(product.getName(), Arrays.asList(categoryName)))
					.collect(Collectors.toList());
		}
		logger.info("Fetched all products for the category {}",categoryName);
		return productsVo;
	}

	@Override
	public ProductVo addProductToCategory(ProductVo productVo) {
		Category category = null;
		Product product = null;
		ProductVo productVoResponse = null;
		if (productVo != null && productVo.getCategories() != null) {
			if (productVo != null && productVo.getName() != null && !productVo.getName().isEmpty()) {
				Optional<Product> productAvailable = productRepository.findByName(productVo.getName());
				if (productAvailable.isPresent())
					product = productAvailable.get();
				else {
					product = new Product();
					product.setName(productVo.getName());
				}

				for (String categoryName : productVo.getCategories()) {
					if (categoryName != null && !categoryName.isEmpty()) {
						Optional<Category> categoryAvailable = categoryRepository.findByName(categoryName);
						if (categoryAvailable.isPresent())
							category = categoryAvailable.get();
						else {
							category = new Category();
							category.setName(categoryName);
							categoryRepository.save(category);
						}
						if (product.getCategories()!=null && !product.getCategories().contains(category)) {
							product.getCategories().add(category);

						}else {
							Set<Category> categories= new HashSet<>();
							categories.add(category);
							product.setCategories(categories);
						}
					}

				}
				productRepository.save(product);
				productVoResponse = new ProductVo(product.getName(),
						product.getCategories().stream().map(cat -> cat.getName()).collect(Collectors.toList()));
			}
		}
		return productVoResponse;
	}

	@Override
	public void updateProduct(String productName, String newProductName) {
		Optional<Product> productAvailable = productRepository.findByName(productName);
		if(productAvailable.isPresent()) {
		 Product product = productAvailable.get();
		 product.setName(newProductName);
		 productRepository.save(product);
		}else {
			throw new NotFoundException(productName);
			
		}
		
	}

	@Override
	public ProductVo createProduct(String productName) {
		if (productName != null && !productName.isEmpty()) {
			Optional<Product> productAvailable=productRepository.findByName(productName);
			if(!productAvailable.isPresent()) {
			Product product = new Product();
			product.setName(productName);
			productRepository.save(product);
			}
			ProductVo productVo = new ProductVo(productName, null);
			return productVo;
		}
		return null;
	}

	@Override
	public void deleteProduct(String productName) {
		Optional<Product> productAvailable = productRepository.findByName(productName);
		if(productAvailable.isPresent()) {
			productRepository.delete(productAvailable.get());
			logger.info("product {} deleted successfully", productName);
		}else {
			throw new NotFoundException(productName);
			
		}
	}

}
