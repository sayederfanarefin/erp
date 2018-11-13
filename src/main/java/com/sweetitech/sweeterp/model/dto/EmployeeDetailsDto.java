package com.sweetitech.sweeterp.model.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;


public class EmployeeDetailsDto {
	
	
	
	private String firstName;
	private String lastName;
	private String designation;
	
	@JsonBackReference(value="user-employeedetails-dto")
	private UserDto user;
	
	private String address;
	private String phone;
	private Date dateOfBirth;
	private Date joiningDate;
	
	@JsonBackReference(value="employeedetails-salary-dto")
	private SalaryDto slaray;
	
	
	
	@Column(name = "created_at")
	public Date createdAt;

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public SalaryDto getSlaray() {
		return slaray;
	}

	public void setSlaray(SalaryDto slaray) {
		this.slaray = slaray;
	}

	
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

	
	public EmployeeDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeDetailsDto(String firstName, String lastName, String designation,  String address,
			String phone, Date dateOfBirth, Date joiningDate, SalaryDto slaray) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.designation = designation;
		
		this.address = address;
		this.phone = phone;

		this.dateOfBirth = dateOfBirth;
		this.joiningDate = joiningDate;
		this.slaray = slaray;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
	
	
}