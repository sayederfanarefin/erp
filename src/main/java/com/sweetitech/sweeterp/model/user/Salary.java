package com.sweetitech.sweeterp.model.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "salary")
public class Salary {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "created_at")
	public Date createdAt;


	@OneToMany
    @JoinColumn(name = "fk_slaray")
	private List<EmployeeDetails> employeeDetails;
	
	private long houseRent;
	private long gross;
	private long medical;
	private long insurance;
	private long allowance;
	
	
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

	public List<EmployeeDetails> getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(List<EmployeeDetails> employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	public long getHouseRent() {
		return houseRent;
	}

	public void setHouseRent(long houseRent) {
		this.houseRent = houseRent;
	}

	public long getGross() {
		return gross;
	}

	public void setGross(long gross) {
		this.gross = gross;
	}

	public long getMedical() {
		return medical;
	}

	public void setMedical(long medical) {
		this.medical = medical;
	}

	public long getInsurance() {
		return insurance;
	}

	public void setInsurance(long insurance) {
		this.insurance = insurance;
	}

	public long getAllowance() {
		return allowance;
	}

	public void setAllowance(long allowance) {
		this.allowance = allowance;
	}

	public Salary(List<EmployeeDetails> employeeDetails, long houseRent, long gross, long medical, long insurance,
			long allowance) {
		super();
		this.employeeDetails = employeeDetails;
		this.houseRent = houseRent;
		this.gross = gross;
		this.medical = medical;
		this.insurance = insurance;
		this.allowance = allowance;
	}

	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
}