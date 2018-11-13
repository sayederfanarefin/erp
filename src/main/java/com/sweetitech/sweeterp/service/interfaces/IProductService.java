package com.sweetitech.sweeterp.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.ProductDto;

import com.sweetitech.sweeterp.model.product.Product;

public interface IProductService {
	Product findById(long id);
    void delete(Product product);
    
    ProductDto findProductDtoById(Long id);
    Page<ProductDto> findAllProductDto( int page);
    
    
    ProductDto addProductDto(ProductDto productDto);
    ProductDto updateProductDto(ProductDto productDto);
    void deleteProductByDto(ProductDto productDto);
	Product updateProduct(Product product);
	
	List<ProductDto> search(String searchString);
}
