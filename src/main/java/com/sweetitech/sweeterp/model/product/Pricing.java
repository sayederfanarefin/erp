package com.sweetitech.sweeterp.model.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sweetitech.sweeterp.model.customer.Customer;

@Entity
@Table(name = "pricing")
public class Pricing {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "created_at")
	public Date createdAt;
	
	private String unite_name;
	
	private double ratePerUnit;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pricings")
	private Product product;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pricing_type")
	private PricingType pricingType;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pricing_customer")
	private Customer customer;

	
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public PricingType getPricingType() {
		return pricingType;
	}

	public void setPricingType(PricingType pricingType) {
		this.pricingType = pricingType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

	
	
}