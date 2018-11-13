package com.sweetitech.sweeterp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.user.Salary;
import com.sweetitech.sweeterp.repository.SalaryRepository;
import com.sweetitech.sweeterp.service.interfaces.ISalaryService;

@Service
@Transactional
public class SalaryService implements ISalaryService {

    @Autowired
    private SalaryRepository salaryRepository;
	
//    @Autowired
//    private ModelMapper modelMapper;
    

	@Override
	public Salary findById(Long id) {
		return salaryRepository.findById(id);
	}

	@Override
	public void delete(Salary salary) {
		salaryRepository.delete(salary);
	}

	
	
//	private PrivilegeDto convertToDto(Salary privilege) {
//		PrivilegeDto privilegeDto = modelMapper.map(privilege, PrivilegeDto.class);		
//	    return privilegeDto;
//	}
//
//	private PrivilegeDtoSimple convertToPrivilegeDtoSimple(Salary privilege) {
//		PrivilegeDtoSimple privilegeDto = modelMapper.map(privilege, PrivilegeDtoSimple.class);		
//	    return privilegeDto;
//	}
	

	@Override
	public Salary updateSalary(Salary salary) {
		
		return salaryRepository.save(salary);
	}

	@Override
	public Page<Salary> findAllSalary(int page) {
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return salaryRepository.findAll(request);
	}

	@Override
	public Salary addSalary(Salary salary) {
		
		return salaryRepository.save(salary);
	}
	
}