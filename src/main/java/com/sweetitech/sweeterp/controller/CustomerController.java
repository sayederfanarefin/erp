package com.sweetitech.sweeterp.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
import com.sweetitech.sweeterp.model.dto.CustomerDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.model.user.Territory;
import com.sweetitech.sweeterp.service.interfaces.ICustomerService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IUserService userService;

	// view all customers
	@GetMapping("/")
	public @ResponseBody ResponseEntity<?> viewAllCustomer(Principal principal) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CUSTOMER_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<CustomerDto>>(customerService.findAllCustomerDto(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view all customers by page
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<?> viewAllCustomerByPage(Principal principal,
			@RequestParam(value = "pageId", required = true) int pageId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CUSTOMER_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<CustomerDto>>(customerService.findAllCustomerDto(pageId), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view all customers by division page
	@GetMapping("/byTerritory/page")
	public @ResponseBody ResponseEntity<?> viewAllCustomerByDivisioByPage(Principal principal,
			@RequestParam(value = "pageId", required = false) Integer pageId,
			@RequestParam(value = "territoryId", required = true) Long territoryId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CUSTOMER_VIEW, userService.findUserByEmail(principal.getName()))) {

			if (pageId == null) {
				pageId = 0;
			}
			return new ResponseEntity<Page<CustomerDto>>(customerService.findAllCustomerDtoByTerritory(territoryId, pageId),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view by id
	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> viewAllCustomerById(Principal principal,
			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CUSTOMER_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<CustomerDto>(customerService.findCustomerDtoById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<?> addCustomer(@Valid Principal principal,

			@RequestBody @Valid final CustomerDto customerDto) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CUSTOMER_ADD, userService.findUserByEmail(principal.getName()))) {

			// System.out.println("->->->->->->->->->->->->->->->->->->->->" +
			// customerDto.getName());;

		
			customerService.addCustomerDto(customerDto, userService.findUserByEmail(principal.getName()));
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateCustomer(@Valid Principal principal, @RequestBody final CustomerDto customerDto) {
		if (CommonMethod.hasPrivilege(PrivilegeConstants.CUSTOMER_UPDATE,
				userService.findUserByEmail(principal.getName()))) {
			customerService.updateCustomerDto(customerDto);
			return new ResponseEntity<String>("Updated!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deleteCustomer(@Valid Principal principal,

			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CUSTOMER_DELETE,
				userService.findUserByEmail(principal.getName()))) {

			customerService.delete(customerService.findById(id));

			return new ResponseEntity<String>("Request Received", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> search(@Valid Principal principal,

			@Valid final String searchString) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CUSTOMER_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<List<CustomerDto>>(customerService.search(searchString), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	@RequestMapping(value = "/searchByMyTerritory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> searchByMyTerritory(@Valid Principal principal,

			@Valid final String searchString) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CUSTOMER_VIEW, userService.findUserByEmail(principal.getName()))) {

			
			List<Long> territoryIds = userService.findUserByEmail(principal.getName()).getTerritories().stream().map(Territory::getId).collect(Collectors.toList());

			return new ResponseEntity<List<CustomerDto>>(customerService.searchWithTerritory(searchString, territoryIds), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
}