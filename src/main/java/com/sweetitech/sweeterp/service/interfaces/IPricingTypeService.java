package com.sweetitech.sweeterp.service.interfaces;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.PricingTypeDto;

import com.sweetitech.sweeterp.model.product.PricingType;

public interface IPricingTypeService {
	PricingType findById(long id);
    void delete(PricingType pricingType);
    PricingType updatePricingType(PricingType pricingType);
    Page<PricingType> findAllPricingType( int page);
  
    PricingTypeDto findPricingTypeDtoById(Long id);
    Page<PricingTypeDto> findAllPricingTypeDto( int page);
    
    
    PricingTypeDto addPricingTypeDto(PricingTypeDto pricingTypeDto);
    PricingTypeDto updatePricingTypeDto(PricingTypeDto pricingTypeDto);
    void deletePricingTypeByDto(PricingTypeDto pricingTypeDto);
    
 
}
