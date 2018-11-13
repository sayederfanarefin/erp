package com.sweetitech.sweeterp.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = UserDtoSimple.class)
public class UserDtoSimple {
	
	private Long id;
	

	private String email;
	

	 //------------------------------------------------------------------methods begins------------------------------------------------------------------//
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public UserDtoSimple() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDtoSimple(String email) {
		super();
		this.email = email;
	}
	
}
