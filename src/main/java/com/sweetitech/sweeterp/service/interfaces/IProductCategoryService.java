package com.sweetitech.sweeterp.service.interfaces;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.ProductCategoryDto;

import com.sweetitech.sweeterp.model.product.ProductCategory;

public interface IProductCategoryService {
	ProductCategory findById(long id);
    void delete(ProductCategory productCategory);
    
    ProductCategoryDto findProductCategoryDtoById(Long id);
    Page<ProductCategoryDto> findAllProductCategoryDto( int page);
    
    
    ProductCategoryDto addProductCategoryDto(ProductCategoryDto productCategoryDto);
    ProductCategoryDto updateProductCategoryDto(ProductCategoryDto productCategoryDto);
    void deleteProductCategoryByDto(ProductCategoryDto productCategoryDto);
    ProductCategory updateProductCategory(ProductCategory productCategory);
	
}
