package com.sweetitech.sweeterp.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerDetailsDto {
	
	private Long id;
	
	
	private String email;
	private String contactPerson;
	private String address;
	
	private String comment;
	
	
	private String phone;
	private String fax;
	
	
	
	@JsonBackReference(value="customer-details")
    private CustomerDto customer;
	
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


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	
	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public CustomerDetailsDto(String email, String contactPerson, String address, String comment,
			String phone, String fax, CustomerDto customer) {
		super();
		
		this.email = email;
		this.contactPerson = contactPerson;
		this.address = address;
		this.comment = comment;
		this.phone = phone;
		this.fax = fax;
	
		this.customer = customer;
	}

	public CustomerDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}