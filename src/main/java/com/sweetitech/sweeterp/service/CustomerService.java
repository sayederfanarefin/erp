package com.sweetitech.sweeterp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sweetitech.sweeterp.model.dto.CustomerDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.customer.Customer;
import com.sweetitech.sweeterp.model.user.User;
import com.sweetitech.sweeterp.repository.CustomerRepository;
import com.sweetitech.sweeterp.service.interfaces.ICustomerService;

@Service
@Transactional
public class CustomerService implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Customer findById(long id) {
		return customerRepository.findById(id);
	}

	@Override
	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Page<Customer> findAllCustomer(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return customerRepository.findAll(request);
	}

	@Override
	public CustomerDto findCustomerDtoById(Long id) {
		// TODO Auto-generated method stub
		return convertToDto(customerRepository.findById(id));
	}

	@Override
	public Page<CustomerDto> findAllCustomerDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Customer> roles = customerRepository.findAll(request);

		Page<CustomerDto> dtoPage = roles.map(new Converter<Customer, CustomerDto>() {
			@Override
			public CustomerDto convert(Customer entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public CustomerDto addCustomerDto(CustomerDto customerDto, User user) {
		// TODO Auto-generated method stub
		Customer customer = convertToEntity(customerDto);
		customer.setCustomerCreatedBy(user);
		customer = customerRepository.save(customer);
		return convertToDto(customer);
	}

	@Override
	public CustomerDto updateCustomerDto(CustomerDto customerDto) {
		return convertToDto(customerRepository.save(convertToEntity(customerDto)));
	}

	@Override
	public void deleteCustomerByDto(CustomerDto customerDto) {
		customerRepository.delete(convertToEntity(customerDto));
	}

	private CustomerDto convertToDto(Customer customer) {
		CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
		return customerDto;
	}

	private Customer convertToEntity(CustomerDto customerDto) {
		Customer customer = modelMapper.map(customerDto, Customer.class);
		return customer;
	}

	@Override
	public Page<CustomerDto> findAllCustomerDtoByTerritory(Long territoryId, int page) {

		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Customer> roles = customerRepository.findAllByCustomerTeritory_Id(territoryId, request);
		Page<CustomerDto> dtoPage = roles.map(new Converter<Customer, CustomerDto>() {
			@Override
			public CustomerDto convert(Customer entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public List<CustomerDto> search(String searchString) {
		List<Customer> customers = customerRepository.findByNameContaining(searchString);
		List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		for (int i = 0; i < customers.size(); i++) {
			CustomerDto temp = convertToDto(customers.get(i));
			customerDtos.add(temp);
		}
		return customerDtos;
	}

	@Override
	public List<CustomerDto> searchWithTerritory(String searchString, List<Long> territoryId) {
		List<Customer> customers = customerRepository.findByNameContainingAndCustomerTeritoryIdIn(searchString,
				territoryId);
		List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		for (int i = 0; i < customers.size(); i++) {
			CustomerDto temp = convertToDto(customers.get(i));
			customerDtos.add(temp);
		}
		return customerDtos;
	}

}