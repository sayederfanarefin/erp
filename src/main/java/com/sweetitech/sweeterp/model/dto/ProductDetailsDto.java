package com.sweetitech.sweeterp.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class ProductDetailsDto {

	private Long id;
	
	private String details;
	
	@JsonBackReference(value="product-details-product-category-dto")
	private ProductCategoryDto productCategory ;
	
	@JsonBackReference(value="product-product-details-dto")
    private ProductDto product;
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public ProductCategoryDto getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategoryDto productCategory) {
		this.productCategory = productCategory;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}
	
}