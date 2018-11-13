package com.sweetitech.sweeterp.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sweetitech.sweeterp.model.dto.OrderDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.customer.Order;
import com.sweetitech.sweeterp.repository.OrderRepository;
import com.sweetitech.sweeterp.service.interfaces.IOrderService;

@Service
@Transactional
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public Order findById(long id) {
		return orderRepository.findById(id);
	}

	@Override
	public void delete(Order order) {
		orderRepository.delete(order);
	}

	@Override
	public Order updateOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Page<Order> findAllOrder(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return orderRepository.findAll(request);
	}


	@Override
	public OrderDto findOrderDtoById(Long id) {
		// TODO Auto-generated method stub
		return convertToDto(orderRepository.findById(id));
	}

	@Override
	public Page<OrderDto> findAllOrderDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Order> roles = orderRepository.findAll(request);

		Page<OrderDto> dtoPage = roles.map(new Converter<Order, OrderDto>() {
			@Override
			public OrderDto convert(Order entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public OrderDto addOrderDto(OrderDto orderDto) {
		// TODO Auto-generated method stub
		Order order = orderRepository.save(convertToEntity(orderDto));
		return convertToDto(order);
	}
	

	@Override
	public OrderDto updateOrderDto(OrderDto orderDto) {
		return convertToDto(orderRepository.save(convertToEntity(orderDto)));
	}

	@Override
	public void deleteOrderByDto(OrderDto orderDto) {
		orderRepository.delete(convertToEntity(orderDto));
	}
	
	
	private OrderDto convertToDto(Order order) {
		OrderDto orderDto = modelMapper.map(order, OrderDto.class);
		return orderDto;
	}
	
	
	private Order convertToEntity(OrderDto orderDto) {
		Order order = modelMapper.map(orderDto, Order.class);
		return order;
	}

	

	
	
}