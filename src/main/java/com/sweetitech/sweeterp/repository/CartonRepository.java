package com.sweetitech.sweeterp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.product.Carton;

public interface CartonRepository extends JpaRepository<Carton, Long> {
	
	Page<Carton> findAll(Pageable pageable);
	Carton findById(Long id);
	
	List<Carton> findByNameContaining(String searchString);
	
}
