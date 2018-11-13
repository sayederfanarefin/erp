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
import com.sweetitech.sweeterp.model.dto.PricingDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.service.interfaces.IPricingService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {

	@Autowired
	private IPricingService pricingService;

	@Autowired
	private IUserService userService;

	// view all pricings
	@GetMapping("/")
	public @ResponseBody ResponseEntity<?> viewAllPricing(Principal principal) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICING_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<PricingDto>>(pricingService.findAllPricingDto(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view all pricings by page
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<?> viewAllPricingByPage(Principal principal,
			@RequestParam(value = "pageId", required = true) int pageId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICING_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<PricingDto>>(pricingService.findAllPricingDto(pageId), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}
	
	


	
	// view by id
	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> viewAllPricingById(Principal principal,
			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICING_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<PricingDto>(pricingService.findPricingDtoById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<?> addPricing(@Valid Principal principal,

			@RequestBody @Valid final PricingDto pricingDto) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICING_ADD, userService.findUserByEmail(principal.getName()))) {

			PricingDto pp = pricingService.specificPricing(pricingDto.getCustomer().getId(), pricingDto.getProduct().getId(), pricingDto.getPricingType().getId());

			if(pp == null) {
				pricingService.addPricingDto(pricingDto);
				return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
			}else {
				return new ResponseEntity<String>("Already Exists!", HttpStatus.CONFLICT);
			}
			
			
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> updatePricing(@Valid Principal principal, @RequestBody final PricingDto pricingDto) {
		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICING_UPDATE,
				userService.findUserByEmail(principal.getName()))) {
			pricingService.updatePricingDto(pricingDto);
			return new ResponseEntity<String>("Updated!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deletePricing(@Valid Principal principal,

			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICING_DELETE,
				userService.findUserByEmail(principal.getName()))) {

			pricingService.delete(pricingService.findById(id));

			return new ResponseEntity<String>("Request Received", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	@RequestMapping(value = "/specific", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> specificPricing(@Valid Principal principal,
			@RequestParam(value = "customerId", required = true) Long customerId,
			@RequestParam(value = "productId", required = true) Long productId,
			@RequestParam(value = "pricingTypeId", required = true) Long pricingTypeId
			) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRICING_ADD, userService.findUserByEmail(principal.getName()))) {

			// System.out.println("->->->->->->->->->->->->->->->->->->->->" +
			// pricingDto.getName());;

			PricingDto pdto = pricingService.specificPricing(customerId, productId, pricingTypeId);
			if(pdto == null) {
				return new ResponseEntity<String>("No record found.", HttpStatus.OK);
			}else {
				return new ResponseEntity<PricingDto>(pdto, HttpStatus.OK);
			}
			
			
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	
	
}