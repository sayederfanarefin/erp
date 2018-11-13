package com.sweetitech.sweeterp.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class PricingDto {

	private Long id;

	public Date createdAt;
	
	private String unite_name;
	
	private double ratePerUnit;
	
	
	private ProductDto product;
	
	//@JsonBackReference(value="pricing-pricing-type")
	private PricingTypeDto pricingType;

	private CustomerDto customer;

	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.000 ", timezone = "UTC")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnite_name() {
		return unite_name;
	}

	public void setUnite_name(String unite_name) {
		this.unite_name = unite_name;
	}

	public double getRatePerUnit() {
		return ratePerUnit;
	}

	public void setRatePerUnit(double ratePerUnit) {
		this.ratePerUnit = ratePerUnit;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public PricingTypeDto getPricingType() {
		return pricingType;
	}

	public void setPricingType(PricingTypeDto pricingType) {
		this.pricingType = pricingType;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	
	

	
	
}