package com.sweetitech.sweeterp.model.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "product_category")
public class ProductCategory {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "created_at")
	public Date createdAt;
	
	@Column(columnDefinition="LONGTEXT")
	private String name;
	
	@OneToMany
    @JoinColumn(name = "fk_productCategory")
	private List<ProductDetails> productsDetails = new ArrayList<ProductDetails>();
	
	
	
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

	public List<ProductDetails> getProductsDetails() {
		return productsDetails;
	}

	public void setProductsDetails(List<ProductDetails> productsDetails) {
		this.productsDetails = productsDetails;
	}

	
	
	
}