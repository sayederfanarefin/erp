package com.sweetitech.sweeterp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.customer.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	
	Order findById(Long id);
	
	
}
