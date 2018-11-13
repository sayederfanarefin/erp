package com.sweetitech.sweeterp.model.dto;

public class RoleDtoJust {

	private Long id;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleDtoJust(Long id, String name) {
		super();
		
		this.id = id;
		this.name = name;
	}

	public RoleDtoJust() {
		super();
		// TODO Auto-generated constructor stub
	}

}
