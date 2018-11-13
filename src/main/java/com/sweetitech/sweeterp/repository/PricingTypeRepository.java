package com.sweetitech.sweeterp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.product.PricingType;

public interface PricingTypeRepository extends JpaRepository<PricingType, Long> {
	
	
	PricingType findById(Long id);
	
	List<PricingType> findByNameContaining(String searchString);
	
	
}
