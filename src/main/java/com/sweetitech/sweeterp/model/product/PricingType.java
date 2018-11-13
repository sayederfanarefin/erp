package com.sweetitech.sweeterp.model.product;

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
@Table(name = "pricing_type")
public class PricingType {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "created_at")
	public Date createdAt;
	
	@Column(columnDefinition="LONGTEXT")
	private String name;
	
	
	@OneToMany
	@JoinColumn(name = "fk_pricing_type")
	private List<Pricing> pricings;
	

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

	public PricingType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PricingType(String name) {
		super();
		this.name = name;
	}

	public List<Pricing> getPricings() {
		return pricings;
	}

	public void setPricings(List<Pricing> pricings) {
		this.pricings = pricings;
	}

	
	
}