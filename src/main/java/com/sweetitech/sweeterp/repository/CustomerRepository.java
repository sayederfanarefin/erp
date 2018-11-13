package com.sweetitech.sweeterp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	

	Customer findById(Long id);
	Page<Customer> findAllByCustomerTeritory_Id(Long territoryId, Pageable pageable);
	
	List<Customer> findByNameContaining(String searchString);
	
	List<Customer> findByNameContainingAndCustomerTeritoryIdIn(String searchString, List<Long> territoryIds);
}
