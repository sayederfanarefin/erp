package com.sweetitech.sweeterp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.user.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
	Salary findById(Long id);
}
