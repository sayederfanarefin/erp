package com.sweetitech.sweeterp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.product.Pricing;

public interface PricingRepository extends JpaRepository<Pricing, Long> {
	
	
	Pricing findById(Long id);
	
	Pricing findByCustomer_IdAndProduct_idAndPricingType_Id(Long customerId, Long productId, Long pricingTypeId);
	
}
