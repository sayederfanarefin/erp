package com.sweetitech.sweeterp.model.dto;

import java.util.Collection;

public class RoleDto {
	private Collection<PrivilegeDto> privileges;

	private Long id;
	
	private String name;

	public Collection<PrivilegeDto> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<PrivilegeDto> privileges) {
		this.privileges = privileges;
	}

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

	public RoleDto(Collection<PrivilegeDto> privileges, Long id, String name) {
		super();
		this.privileges = privileges;
		this.id = id;
		this.name = name;
	}

	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
