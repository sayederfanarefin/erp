package com.sweetitech.sweeterp.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sweetitech.sweeterp.model.dto.PricingTypeDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.product.PricingType;
import com.sweetitech.sweeterp.repository.PricingTypeRepository;
import com.sweetitech.sweeterp.service.interfaces.IPricingTypeService;

@Service
@Transactional
public class PricingTypeService implements IPricingTypeService {

	@Autowired
	private PricingTypeRepository pricingTypeRepository;

	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public PricingType findById(long id) {
		return pricingTypeRepository.findById(id);
	}

	@Override
	public void delete(PricingType pricingType) {
		pricingTypeRepository.delete(pricingType);
	}

	@Override
	public PricingType updatePricingType(PricingType pricingType) {
		return pricingTypeRepository.save(pricingType);
	}

	@Override
	public Page<PricingType> findAllPricingType(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return pricingTypeRepository.findAll(request);
	}


	@Override
	public PricingTypeDto findPricingTypeDtoById(Long id) {
		// TODO Auto-generated method stub
		return convertToDto(pricingTypeRepository.findById(id));
	}

	@Override
	public Page<PricingTypeDto> findAllPricingTypeDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<PricingType> roles = pricingTypeRepository.findAll(request);

		Page<PricingTypeDto> dtoPage = roles.map(new Converter<PricingType, PricingTypeDto>() {
			@Override
			public PricingTypeDto convert(PricingType entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public PricingTypeDto addPricingTypeDto(PricingTypeDto pricingTypeDto) {
		// TODO Auto-generated method stub
		PricingType pricingType = pricingTypeRepository.save(convertToEntity(pricingTypeDto));
		return convertToDto(pricingType);
	}
	

	@Override
	public PricingTypeDto updatePricingTypeDto(PricingTypeDto pricingTypeDto) {
		return convertToDto(pricingTypeRepository.save(convertToEntity(pricingTypeDto)));
	}

	@Override
	public void deletePricingTypeByDto(PricingTypeDto pricingTypeDto) {
		pricingTypeRepository.delete(convertToEntity(pricingTypeDto));
	}
	
	
	private PricingTypeDto convertToDto(PricingType pricingType) {
		PricingTypeDto pricingTypeDto = modelMapper.map(pricingType, PricingTypeDto.class);
		return pricingTypeDto;
	}
	
	
	private PricingType convertToEntity(PricingTypeDto pricingTypeDto) {
		PricingType pricingType = modelMapper.map(pricingTypeDto, PricingType.class);
		return pricingType;
	}

	

	
	
}