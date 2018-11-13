package com.sweetitech.sweeterp.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class CartonDto {


	private Long id;

	public Date createdAt;
	
	private String name;
	
	private Long units;
	
	private ProductDto product;
	

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

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUnits() {
		return units;
	}

	public void setUnits(Long units) {
		this.units = units;
	}

	
}