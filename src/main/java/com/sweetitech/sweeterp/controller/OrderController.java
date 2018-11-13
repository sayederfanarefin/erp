package com.sweetitech.sweeterp.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.sweetitech.sweeterp.model.dto.OrderDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.service.interfaces.IOrderService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IUserService userService;

	// view all orders
	@GetMapping("/")
	public @ResponseBody ResponseEntity<?> viewAllOrder(Principal principal) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.ORDER_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<OrderDto>>(orderService.findAllOrderDto(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view all orders by page
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<?> viewAllOrderByPage(Principal principal,
			@RequestParam(value = "pageId", required = true) int pageId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.ORDER_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<OrderDto>>(orderService.findAllOrderDto(pageId), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}
	
	


	
	// view by id
	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> viewAllOrderById(Principal principal,
			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.ORDER_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<OrderDto>(orderService.findOrderDtoById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<?> addOrder(@Valid Principal principal,

			@RequestBody @Valid final OrderDto orderDto) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.ORDER_ADD, userService.findUserByEmail(principal.getName()))) {

			// System.out.println("->->->->->->->->->->->->->->->->->->->->" +
			// orderDto.getName());;

			orderService.addOrderDto(orderDto);
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateOrder(@Valid Principal principal, @RequestBody final OrderDto orderDto) {
		if (CommonMethod.hasPrivilege(PrivilegeConstants.ORDER_UPDATE,
				userService.findUserByEmail(principal.getName()))) {
			orderService.updateOrderDto(orderDto);
			return new ResponseEntity<String>("Updated!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deleteOrder(@Valid Principal principal,

			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.ORDER_DELETE,
				userService.findUserByEmail(principal.getName()))) {

			orderService.delete(orderService.findById(id));

			return new ResponseEntity<String>("Request Received", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	
}