package com.sweetitech.sweeterp.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class ProductCategoryDto {

	private Long id;
	private String name;
	
	@JsonManagedReference(value="product-details-product-category-dto")
	private List<ProductDetailsDto> productsDetails = new ArrayList<ProductDetailsDto>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductDetailsDto> getProductsDetails() {
		return productsDetails;
	}

	public void setProductsDetails(List<ProductDetailsDto> productsDetails) {
		this.productsDetails = productsDetails;
	}

	
	
	
}