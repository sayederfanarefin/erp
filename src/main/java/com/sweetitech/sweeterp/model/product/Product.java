package com.sweetitech.sweeterp.model.product;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "created_at")
	public Date createdAt;
	
	@Column(columnDefinition="LONGTEXT")
	private String particulars;
	

	@OneToMany
	@JoinColumn(name = "fk_pricings")
	private List<Pricing> pricings;
	
	@OneToMany
	@JoinColumn(name = "fk_product_carton")
	private List<Carton> cartons;
	
	private String unit;
	
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "product")
	private ProductDetails productDetails;
	
	
	

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

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

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public List<Pricing> getPricings() {
		return pricings;
	}

	public void setPricings(List<Pricing> pricings) {
		this.pricings = pricings;
	}

	public List<Carton> getCartons() {
		return cartons;
	}

	public void setCartons(List<Carton> cartons) {
		this.cartons = cartons;
	}
	
	
	
}