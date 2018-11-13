package com.sweetitech.sweeterp.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sweetitech.sweeterp.model.dto.PricingDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.product.Pricing;
import com.sweetitech.sweeterp.repository.PricingRepository;
import com.sweetitech.sweeterp.service.interfaces.IPricingService;

@Service
@Transactional
public class PricingService implements IPricingService {

	@Autowired
	private PricingRepository pricingRepository;

	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public Pricing findById(long id) {
		return pricingRepository.findById(id);
	}

	@Override
	public void delete(Pricing pricing) {
		pricingRepository.delete(pricing);
	}

	@Override
	public Pricing updatePricing(Pricing pricing) {
		return pricingRepository.save(pricing);
	}

	@Override
	public Page<Pricing> findAllPricing(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return pricingRepository.findAll(request);
	}


	@Override
	public PricingDto findPricingDtoById(Long id) {
		// TODO Auto-generated method stub
		return convertToDto(pricingRepository.findById(id));
	}

	@Override
	public Page<PricingDto> findAllPricingDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Pricing> roles = pricingRepository.findAll(request);

		Page<PricingDto> dtoPage = roles.map(new Converter<Pricing, PricingDto>() {
			@Override
			public PricingDto convert(Pricing entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public PricingDto addPricingDto(PricingDto pricingDto) {
		// TODO Auto-generated method stub
		Pricing pricing = pricingRepository.save(convertToEntity(pricingDto));
		return convertToDto(pricing);
	}
	

	@Override
	public PricingDto updatePricingDto(PricingDto pricingDto) {
		return convertToDto(pricingRepository.save(convertToEntity(pricingDto)));
	}

	@Override
	public void deletePricingByDto(PricingDto pricingDto) {
		pricingRepository.delete(convertToEntity(pricingDto));
	}
	
	
	private PricingDto convertToDto(Pricing pricing) {
		PricingDto pricingDto = modelMapper.map(pricing, PricingDto.class);
		return pricingDto;
	}
	
	
	private Pricing convertToEntity(PricingDto pricingDto) {
		Pricing pricing = modelMapper.map(pricingDto, Pricing.class);
		return pricing;
	}

	@Override
	public PricingDto specificPricing(Long customerId, Long productId, Long pricingTypeId) {
		Pricing pricing = pricingRepository.findByCustomer_IdAndProduct_idAndPricingType_Id(customerId, productId, pricingTypeId);
		if(pricing == null) {
			return null;
		}else {
			return convertToDto(pricing);
		}
		
	}
	

}