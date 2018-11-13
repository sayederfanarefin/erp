package com.sweetitech.sweeterp.service.interfaces;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.PricingDto;

import com.sweetitech.sweeterp.model.product.Pricing;

public interface IPricingService {
	Pricing findById(long id);
    void delete(Pricing pricing);
    Pricing updatePricing(Pricing pricing);
    Page<Pricing> findAllPricing( int page);
  
    PricingDto findPricingDtoById(Long id);
    Page<PricingDto> findAllPricingDto( int page);
    
    
    PricingDto addPricingDto(PricingDto pricingDto);
    PricingDto updatePricingDto(PricingDto pricingDto);
    void deletePricingByDto(PricingDto pricingDto);
  
    PricingDto specificPricing(Long customerId, Long productId, Long pricingTypeId);
}
