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
import com.sweetitech.sweeterp.model.dto.PricingTypeDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.service.interfaces.IPricingTypeService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/pricingType")
public class PricingTypeController {

	@Autowired
	private IPricingTypeService pricingTypeService;

	@Autowired
	private IUserService userService;

	// view all pricingTypes
	@GetMapping("/")
	public @ResponseBody ResponseEntity<?> viewAllPricingType(Principal principal) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICINGTYPE_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<PricingTypeDto>>(pricingTypeService.findAllPricingTypeDto(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view all pricingTypes by page
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<?> viewAllPricingTypeByPage(Principal principal,
			@RequestParam(value = "pageId", required = true) int pageId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICINGTYPE_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<PricingTypeDto>>(pricingTypeService.findAllPricingTypeDto(pageId), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}
	

	// view by id
	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> viewAllPricingTypeById(Principal principal,
			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICINGTYPE_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<PricingTypeDto>(pricingTypeService.findPricingTypeDtoById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<?> addPricingType(@Valid Principal principal,

			@RequestBody @Valid final PricingTypeDto pricingTypeDto) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICINGTYPE_ADD, userService.findUserByEmail(principal.getName()))) {

			// System.out.println("->->->->->->->->->->->->->->->->->->->->" +
			// pricingTypeDto.getName());;

			pricingTypeService.addPricingTypeDto(pricingTypeDto);
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> updatePricingType(@Valid Principal principal, @RequestBody final PricingTypeDto pricingTypeDto) {
		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICINGTYPE_UPDATE,
				userService.findUserByEmail(principal.getName()))) {
			pricingTypeService.updatePricingTypeDto(pricingTypeDto);
			return new ResponseEntity<String>("Updated!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deletePricingType(@Valid Principal principal,

			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICINGTYPE_DELETE,
				userService.findUserByEmail(principal.getName()))) {

			pricingTypeService.delete(pricingTypeService.findById(id));

			return new ResponseEntity<String>("Request Received", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	
	

}