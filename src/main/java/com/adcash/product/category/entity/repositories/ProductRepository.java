package com.adcash.product.category.entity.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adcash.product.category.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
 
	@Query(value="select * from product p ,category c join" + 
			" product_categories pc on  pc.product_id=p.id and pc.categories_id= c.id" + 
			" where c.name=?1", nativeQuery = true)
	List<Product> findProductsByCategory(String categoryName);
	Optional<Product> findByName(String name);
}
