package com.sweetitech.sweeterp.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


public class DivisionDto {

	
	private Long id;
	
	private String name;
	
	@JsonManagedReference(value="division-area")
	private List<AreaDto> areas = new ArrayList<AreaDto>();
	
	
//	public Date createdAt;
//
//	
//
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.000 ", timezone = "UTC")
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}

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

	public List<AreaDto> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaDto> areas) {
		this.areas = areas;
	}

	public DivisionDto(String name, List<AreaDto> areas) {
		super();
		this.name = name;
		this.areas = areas;
	}

	public DivisionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}