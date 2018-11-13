package com.sweetitech.sweeterp.model.dto;

public class OrderItemDto {

	private Long id;
	
	
	
	private OrderDto order;
	
	
	
	private ProductDto product ;// = new ArrayList<ProductDto>();
	
	
	    private String packaging;
	    private String quantPerUnit;
	    private PricingTypeDto pricing;
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

	

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public OrderDto getOrder() {
		return order;
	}

	public void setOrder(OrderDto order) {
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

	public PricingTypeDto getPricing() {
		return pricing;
	}

	public void setPricing(PricingTypeDto pricing) {
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