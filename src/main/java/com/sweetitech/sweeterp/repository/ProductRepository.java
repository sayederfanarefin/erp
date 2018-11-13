package com.sweetitech.sweeterp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Page<Product> findAll(Pageable pageable);
	Product findById(Long id);
	
	List<Product> findByParticularsContaining(String searchString);
}
