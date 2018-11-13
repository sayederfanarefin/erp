package com.sweetitech.sweeterp.model.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sweetitech.sweeterp.model.product.Pricing;
import com.sweetitech.sweeterp.model.user.Territory;
import com.sweetitech.sweeterp.model.user.User;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name = "fk_customerTeritory")
	private Territory customerTeritory;
	
	@OneToMany
    @JoinColumn(name = "fk_order_customer")
	private List<Order> orders  = new ArrayList<Order>();
	
	@ManyToOne
	@JoinColumn(name = "fk_customerCreatedBy")
	private User customerCreatedBy;
	
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "customer")
	private CustomerDetails customerDetails;
	
	
	@OneToMany
    @JoinColumn(name = "fk_pricing_customer")
	private List<Pricing> pricings  = new ArrayList<Pricing>();
	
	
	@Column(name = "created_at")
	public Date createdAt;

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}

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

	public Territory getCustomerTeritory() {
		return customerTeritory;
	}

	public void setCustomerTeritory(Territory customerTeritory) {
		this.customerTeritory = customerTeritory;
	}

	public User getCustomerCreatedBy() {
		return customerCreatedBy;
	}

	public void setCustomerCreatedBy(User customerCreatedBy) {
		this.customerCreatedBy = customerCreatedBy;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public List<Pricing> getPricings() {
		return pricings;
	}

	public void setPricings(List<Pricing> pricings) {
		this.pricings = pricings;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
	
}