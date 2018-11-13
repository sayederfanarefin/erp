package com.sweetitech.sweeterp.model.dto;

public class PrivilegeDtoSUperSimple {
    private String name;

    private Long id;
    
    private String tag;
    
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

	

	public PrivilegeDtoSUperSimple() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public PrivilegeDtoSUperSimple(String name, Long id, String tag) {
		super();
		this.name = name;
		this.id = id;
		this.tag = tag;
	}
    
    
}
