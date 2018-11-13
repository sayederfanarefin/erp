package com.sweetitech.sweeterp.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class OrderDto {

	private Long id;

	private String comment;
	
	
	private UserDto orderCreatedBy;
	

	private Date manualDate;
	

	private CustomerDto customer;
	
	
	private List<OrderItemDto> orderItem  = new ArrayList<OrderItemDto>();
	
	
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UserDto getOrderCreatedBy() {
		return orderCreatedBy;
	}

	public void setOrderCreatedBy(UserDto orderCreatedBy) {
		this.orderCreatedBy = orderCreatedBy;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public List<OrderItemDto> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItemDto> orderItem) {
		this.orderItem = orderItem;
	}

	public Date getManualDate() {
		return manualDate;
	}

	public void setManualDate(Date manualDate) {
		this.manualDate = manualDate;
	}

	
	
	
	
}