package com.sweetitech.sweeterp.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sweetitech.sweeterp.model.product.PricingType;
import com.sweetitech.sweeterp.model.product.Product;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_order_item")
	private Order order;
	
	@OneToOne
    @JoinColumn(name = "fk_product_order")
	private Product product  ;//= new ArrayList<Product>();
	
	
	private String packaging;
    private String quantPerUnit;
    
    
    @OneToOne
    @JoinColumn(name = "fk_product_pricing_type")
    private PricingType pricing;
    
    
    private double rate;
    private String unitname;
    private Long totalQuantity;
    
    private Long quantity;
    private double  amount;
    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getQuantPerUnit() {
		return quantPerUnit;
	}

	public void setQuantPerUnit(String quantPerUnit) {
		this.quantPerUnit = quantPerUnit;
	}

	public PricingType getPricing() {
		return pricing;
	}

	public void setPricing(PricingType pricing) {
		this.pricing = pricing;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public Long getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	
	
}