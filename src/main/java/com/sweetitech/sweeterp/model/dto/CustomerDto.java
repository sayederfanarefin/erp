package com.sweetitech.sweeterp.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class CustomerDto {
	
	private Long id;
	
	private String name;
	
//	@JsonBackReference(value="customer-territory")
	private TerritoryDto customerTeritory;
	
	private UserDto customerCreatedBy;
	
	@JsonManagedReference(value="customer-details")
	private CustomerDetailsDto customerDetails;
	
	public Date createdAt;


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

	public TerritoryDto getCustomerTeritory() {
		return customerTeritory;
	}

	public void setCustomerTeritory(TerritoryDto customerTeritory) {
		this.customerTeritory = customerTeritory;
	}

	public UserDto getCustomerCreatedBy() {
		return customerCreatedBy;
	}

	public void setCustomerCreatedBy(UserDto customerCreatedBy) {
		this.customerCreatedBy = customerCreatedBy;
	}

	public CustomerDetailsDto getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetailsDto customerDetails) {
		this.customerDetails = customerDetails;
	}

	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}