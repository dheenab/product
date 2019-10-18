package com.adcash.product.category.entity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adcash.product.category.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
 
	@Query(value="select * from products p from products join category cg on  cg.id=p.categoryid where cg.name=?1", nativeQuery = true)
	List<Product> findProductsByCategory(String categoryName);
	Product findByName(String name);
}
