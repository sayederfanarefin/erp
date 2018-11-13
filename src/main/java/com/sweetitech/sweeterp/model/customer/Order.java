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
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sweetitech.sweeterp.model.user.User;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String status;
	
	@Column(columnDefinition="LONGTEXT")
	private String comment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_orderCreatedBy")
	private User orderCreatedBy;
	
	private Date manualDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_order_customer")
	private Customer customer;

	
	
	@OneToMany(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "fk_order_item")
	private List<OrderItem> orderItem  = new ArrayList<OrderItem>();
	
	
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getOrderCreatedBy() {
		return orderCreatedBy;
	}

	public void setOrderCreatedBy(User orderCreatedBy) {
		this.orderCreatedBy = orderCreatedBy;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	public Date getManualDate() {
		return manualDate;
	}

	public void setManualDate(Date manualDate) {
		this.manualDate = manualDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	
}