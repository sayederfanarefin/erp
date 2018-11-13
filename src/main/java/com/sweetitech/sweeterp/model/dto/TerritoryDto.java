package com.sweetitech.sweeterp.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = TerritoryDto.class)
public class TerritoryDto {

	
	private Long id;
	private String name;
	
	@JsonBackReference(value="area-territory")
	private AreaDto area;
	
//	@JsonManagedReference(value="customer-territory")
//	private List<CustomerDto> customers;
	
//	// @JsonBackReference(value="user-territory-dto-2")
//    private List<UserDto> users = new ArrayList<UserDto>();
    
    //------------------------------------------------------------------methods begins------------------------------------------------------------------//

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AreaDto getArea() {
		return area;
	}

	public void setArea(AreaDto area) {
		this.area = area;
	}

//	public List<CustomerDto> getCustomers() {
//		return customers;
//	}
//
//	public void setCustomers(List<CustomerDto> customers) {
//		this.customers = customers;
//	}

//	public List<UserDto> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<UserDto> users) {
//		this.users = users;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TerritoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}