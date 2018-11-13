package com.sweetitech.sweeterp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sweetitech.sweeterp.model.dto.ProductDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.product.Product;
import com.sweetitech.sweeterp.repository.ProductRepository;
import com.sweetitech.sweeterp.service.interfaces.IProductService;

@Service
@Transactional
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public Product findById(long id) {
		return productRepository.findById(id);
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}


	@Override
	public ProductDto findProductDtoById(Long id) {
		
		return convertToDto(productRepository.findById(id));
	}

	@Override
	public Page<ProductDto> findAllProductDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Product> roles = productRepository.findAll(request);

		Page<ProductDto> dtoPage = roles.map(new Converter<Product, ProductDto>() {
			@Override
			public ProductDto convert(Product entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public ProductDto addProductDto(ProductDto productDto) {
		
		Product product = productRepository.save(convertToEntity(productDto));
		return convertToDto(product);
	}
	

	@Override
	public ProductDto updateProductDto(ProductDto productDto) {
		return convertToDto(productRepository.save(convertToEntity(productDto)));
	}

	@Override
	public void deleteProductByDto(ProductDto productDto) {
		productRepository.delete(convertToEntity(productDto));
	}
	
	
	@Override
	public List<ProductDto> search(String searchString) {
		List<Product> areas = productRepository.findByParticularsContaining(searchString);
		List<ProductDto> areaDtos = new ArrayList<ProductDto>();
		for(int i=0; i < areas.size();i++) {
			ProductDto temp = convertToDto(areas.get(i));
			areaDtos.add(temp);
		}
		return areaDtos;
	}
	
	private ProductDto convertToDto(Product product) {
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
	}
	
	
	private Product convertToEntity(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		return product;
	}
	
}