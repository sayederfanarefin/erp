package com.sweetitech.sweeterp.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = UserDto.class)
public class UserDto {
	
	private Long id;
	
	@JsonManagedReference(value="user-employeedetails-dto")
	private EmployeeDetailsDto employeeDetails;

	private String email;

	private String password;

	//@JsonManagedReference(value="user-territory-dto-2")
	
	
	private List<TerritoryDto> territories = new ArrayList<TerritoryDto>();
	
	private Collection<RoleDtoJust> roles;


	 //------------------------------------------------------------------methods begins------------------------------------------------------------------//
	
	public EmployeeDetailsDto getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(EmployeeDetailsDto employeeDetails) {
		this.employeeDetails = employeeDetails;
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

	public List<TerritoryDto> getTerritories() {
		return territories;
	}

	public void setTerritories(List<TerritoryDto> territories) {
		this.territories = territories;
	}

	public Collection<RoleDtoJust> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleDtoJust> roles) {
		this.roles = roles;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto( EmployeeDetailsDto employeeDetails, String email, String password,
			List<TerritoryDto> territories, Collection<RoleDtoJust> roles) {
		super();
		
		this.employeeDetails = employeeDetails;
		this.email = email;
		this.password = password;
		this.territories = territories;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
