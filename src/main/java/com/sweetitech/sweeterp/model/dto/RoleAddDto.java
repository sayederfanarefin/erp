package com.sweetitech.sweeterp.model.dto;

import java.util.Collection;

public class RoleAddDto {
	private Collection<PrivilegeDto> privileges;

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

	public RoleAddDto(Collection<PrivilegeDto> privileges, String name) {
		super();
		this.privileges = privileges;
		this.name = name;
	}

	public RoleAddDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
