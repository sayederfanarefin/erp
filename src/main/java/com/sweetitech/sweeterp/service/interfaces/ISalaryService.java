package com.sweetitech.sweeterp.service.interfaces;

import org.springframework.data.domain.Page;

import com.sweetitech.sweeterp.model.user.Salary;

public interface ISalaryService {

    void delete(Salary salary);
    Salary updateSalary(Salary salary);
    Page<Salary> findAllSalary( int page);
  
    Salary addSalary(Salary salary);
	Salary findById(Long id);
    
}
