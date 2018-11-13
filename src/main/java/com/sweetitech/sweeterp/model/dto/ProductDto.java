package com.sweetitech.sweeterp.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class ProductDto {

	private Long id;
	
	private String particulars;
	
	private long quantityPerPackage;
	
	private String unit;
	
	@JsonManagedReference(value="product-product-details-dto")
	private ProductDetailsDto productDetails;
	
	
	public long getQuantityPerPackage() {
		return quantityPerPackage;
	}

	public void setQuantityPerPackage(long quantityPerPackage) {
		this.quantityPerPackage = quantityPerPackage;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public ProductDetailsDto getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetailsDto productDetails) {
		this.productDetails = productDetails;
	}
	
}