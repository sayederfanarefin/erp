package com.sweetitech.sweeterp.model.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PricingTypeDto {

	private Long id;
	
	public Date createdAt;
	
	private String name;
	
//	@JsonManagedReference(value="pricing-pricing-type")
//	private List<PricingDto> pricings;
	
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PricingTypeDto() {
		super();
		
	}

	public PricingTypeDto(String name) {
		super();
		this.name = name;
	}

//	public List<PricingDto> getPricings() {
//		return pricings;
//	}
//
//	public void setPricings(List<PricingDto> pricings) {
//		this.pricings = pricings;
//	}

	
	
}