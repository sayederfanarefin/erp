package com.sweetitech.sweeterp.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sweetitech.sweeterp.model.dto.ProductCategoryDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.product.ProductCategory;
import com.sweetitech.sweeterp.repository.ProductCategoryRepository;
import com.sweetitech.sweeterp.service.interfaces.IProductCategoryService;

@Service
@Transactional
public class ProductCategoryService implements IProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public ProductCategory findById(long id) {
		return productCategoryRepository.findById(id);
	}

	@Override
	public void delete(ProductCategory productCategory) {
		productCategoryRepository.delete(productCategory);
	}

	@Override
	public ProductCategory updateProductCategory(ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}


	@Override
	public ProductCategoryDto findProductCategoryDtoById(Long id) {
		
		return convertToDto(productCategoryRepository.findById(id));
	}

	@Override
	public Page<ProductCategoryDto> findAllProductCategoryDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<ProductCategory> roles = productCategoryRepository.findAll(request);

		Page<ProductCategoryDto> dtoPage = roles.map(new Converter<ProductCategory, ProductCategoryDto>() {
			@Override
			public ProductCategoryDto convert(ProductCategory entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public ProductCategoryDto addProductCategoryDto(ProductCategoryDto productCategoryDto) {
		
		ProductCategory productCategory = productCategoryRepository.save(convertToEntity(productCategoryDto));
		return convertToDto(productCategory);
	}
	

	@Override
	public ProductCategoryDto updateProductCategoryDto(ProductCategoryDto productCategoryDto) {
		return convertToDto(productCategoryRepository.save(convertToEntity(productCategoryDto)));
	}

	@Override
	public void deleteProductCategoryByDto(ProductCategoryDto productCategoryDto) {
		productCategoryRepository.delete(convertToEntity(productCategoryDto));
	}
	
	
	private ProductCategoryDto convertToDto(ProductCategory productCategory) {
		ProductCategoryDto productCategoryDto = modelMapper.map(productCategory, ProductCategoryDto.class);
		return productCategoryDto;
	}
	
	
	private ProductCategory convertToEntity(ProductCategoryDto productCategoryDto) {
		ProductCategory productCategory = modelMapper.map(productCategoryDto, ProductCategory.class);
		return productCategory;
	}
	
}