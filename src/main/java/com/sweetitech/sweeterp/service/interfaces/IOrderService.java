package com.sweetitech.sweeterp.service.interfaces;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.OrderDto;

import com.sweetitech.sweeterp.model.customer.Order;

public interface IOrderService {
	Order findById(long id);
    void delete(Order order);
    Order updateOrder(Order order);
    Page<Order> findAllOrder( int page);
  
    OrderDto findOrderDtoById(Long id);
    Page<OrderDto> findAllOrderDto( int page);
    
    
    OrderDto addOrderDto(OrderDto orderDto);
    OrderDto updateOrderDto(OrderDto orderDto);
    void deleteOrderByDto(OrderDto orderDto);
  
}
