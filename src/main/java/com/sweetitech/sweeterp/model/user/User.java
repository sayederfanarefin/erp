package com.sweetitech.sweeterp.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sweetitech.sweeterp.model.customer.Customer;
import com.sweetitech.sweeterp.model.customer.Order;

@Entity
@Table(name = "user_account")
public class User implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_at")
	public Date createdAt;

	@OneToMany
	@JoinColumn(name = "fk_customerCreatedBy")
	private List<Customer> createdCustomers;

	@OneToMany
	@JoinColumn(name = "fk_orderCreatedBy")
	private List<Order> orders = new ArrayList<Order>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private EmployeeDetails employeeDetails;

	private String email;

	private String password;

	private boolean enabled;

	private boolean accountNonExpired;

	private boolean accountNonLocked;

	private boolean credentialsNonExpired;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "territory_user", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "territory_id") })

	private List<Territory> territories = new ArrayList<Territory>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

	private Collection<Role> roles;

	// -----------------methods-----------------//

	public User() {
		super();

		this.enabled = true;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
	}

	public User(List<Customer> createdCustomers, EmployeeDetails employeeDetails, String email, String password,
			List<Territory> territories, Collection<Role> roles) {
		super();
		this.createdCustomers = createdCustomers;
		this.employeeDetails = employeeDetails;
		this.email = email;
		this.password = password;
		this.territories = territories;
		this.roles = roles;

		// --------defaults------
		this.enabled = true;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("-----------------getAuthorities");
		return getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
				.collect(Collectors.toList());
	}

	private List<String> getPrivileges(Collection<Role> roles) {
		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
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

	public List<Customer> getCreatedCustomers() {
		return createdCustomers;
	}

	public void setCreatedCustomers(List<Customer> createdCustomers) {
		this.createdCustomers = createdCustomers;
	}

	public EmployeeDetails getEmployeeDetails() {

		return employeeDetails;
	}

	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		employeeDetails.setUser(this);
		this.employeeDetails = employeeDetails;
	}

	public List<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(List<Territory> territories) {

		this.territories = territories;
	}

	public Territory setTerritory(Territory territory) {

		this.territories.add(territory);

		List<User> users = territory.getUsers();
		users.add(this);
		territory.setUsers(users);

		return territory;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
