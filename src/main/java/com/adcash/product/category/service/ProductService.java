package com.adcash.product.category.service;

import java.util.List;

import com.adcash.product.category.entity.CategoryVo;
import com.adcash.product.category.entity.ProductVo;

public interface ProductService {
    List<ProductVo> getProductsByCategory(String categoryName);
    
    ProductVo addProductToCategory(ProductVo productVo);
    
    void updateProduct(String categoryName, String productName);
    
    ProductVo createProduct(String categoryName);
    
    void deleteProduct(String productName);

}
