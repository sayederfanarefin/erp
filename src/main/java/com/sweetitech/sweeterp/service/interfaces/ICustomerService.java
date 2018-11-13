package com.sweetitech.sweeterp.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.CustomerDto;

import com.sweetitech.sweeterp.model.customer.Customer;
import com.sweetitech.sweeterp.model.user.User;

public interface ICustomerService {
	Customer findById(long id);
    void delete(Customer customer);
    Customer updateCustomer(Customer customer);
    Page<Customer> findAllCustomer( int page);
  
    CustomerDto findCustomerDtoById(Long id);
    Page<CustomerDto> findAllCustomerDto( int page);
    
    
    Page<CustomerDto> findAllCustomerDtoByTerritory(Long territoryId, int page);
    
    
    CustomerDto addCustomerDto(CustomerDto customerDto, User user);
    CustomerDto updateCustomerDto(CustomerDto customerDto);
    void deleteCustomerByDto(CustomerDto customerDto);
    
    List<CustomerDto> search(String searchString);
    List<CustomerDto> searchWithTerritory(String searchString, List<Long> territoryIds);
}
