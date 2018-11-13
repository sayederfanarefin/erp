package com.sweetitech.sweeterp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.product.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
	
	Page<ProductCategory> findAll(Pageable pageable);
	ProductCategory findById(Long id);
}
