package com.sweetitech.sweeterp.model.user;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sweetitech.sweeterp.model.customer.Customer;

@Entity
@Table(name = "territory")
public class Territory {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_area")
	private Area area;
	
	@OneToMany
    @JoinColumn(name = "fk_customerTeritory")
	private List<Customer> customers;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            mappedBy = "territories")

    private List<User> users = new ArrayList<User>();

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

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		this.users.add(user);
		user.getTerritories().add(this);
		
	}
	
	public Territory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	public void addCustomer(Customer customer) {
//		customers.add(customer);
//	}
	
	
}