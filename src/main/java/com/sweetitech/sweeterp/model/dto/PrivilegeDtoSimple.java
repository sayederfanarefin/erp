package com.sweetitech.sweeterp.model.dto;

public class PrivilegeDtoSimple {
    private String name;

    private Long id;
    
    
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

	

	public PrivilegeDtoSimple(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
		
	}

	

	public PrivilegeDtoSimple() {
		super();
		
	}
    
    
}
