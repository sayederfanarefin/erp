package com.sweetitech.sweeterp.model.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "area")
public class Area {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_division")
	@JsonBackReference(value="division-area-entity")
	private Division division;
	
	@OneToMany
    @JoinColumn(name = "fk_area")
	private List<Territory> territories  = new ArrayList<Territory>();
	
	@Column(name = "created_at")
	public Date createdAt;

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

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public List<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(List<Territory> territories) {
		this.territories = territories;
	}
	
	public void addTerritory(Territory territory) {
		territory.setArea(this);
		this.territories.add(territory);
	}
	

	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}