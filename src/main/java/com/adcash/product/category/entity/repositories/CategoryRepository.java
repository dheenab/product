package com.adcash.product.category.entity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adcash.product.category.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Category findByName(String name); 
}
