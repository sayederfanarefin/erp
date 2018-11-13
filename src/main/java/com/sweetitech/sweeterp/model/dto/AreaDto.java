package com.sweetitech.sweeterp.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class AreaDto {

	
	private String name;
	
	private Long id;
	
	@JsonBackReference(value="division-area")
	private DivisionDto division;
	
	@JsonManagedReference(value="area-territory")
	private List<TerritoryDto> territories  = new ArrayList<TerritoryDto>();
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DivisionDto getDivision() {
		return division;
	}

	public void setDivision(DivisionDto division) {
		this.division = division;
	}

	public List<TerritoryDto> getTerritories() {
		return territories;
	}

	public void setTerritories(List<TerritoryDto> territories) {
		this.territories = territories;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AreaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}