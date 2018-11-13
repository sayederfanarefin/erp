package com.sweetitech.sweeterp.controller;

import java.security.Principal;
import java.util.List;

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
import com.sweetitech.sweeterp.model.dto.CartonDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.service.interfaces.ICartonService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/carton")
public class CartonController {

	@Autowired
	private ICartonService cartonService;

	@Autowired
	private IUserService userService;

	// view all cartons
	@GetMapping("/")
	public @ResponseBody ResponseEntity<?> viewAllCarton(Principal principal) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CARTON_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<CartonDto>>(cartonService.findAllCartonDto(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view all cartons by page
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<?> viewAllCartonByPage(Principal principal,
			@RequestParam(value = "pageId", required = true) int pageId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CARTON_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<CartonDto>>(cartonService.findAllCartonDto(pageId), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}
	

	// view by id
	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> viewAllCartonById(Principal principal,
			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CARTON_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<CartonDto>(cartonService.findCartonDtoById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<?> addCarton(@Valid Principal principal,

			@RequestBody @Valid final CartonDto cartonDto) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CARTON_ADD, userService.findUserByEmail(principal.getName()))) {

			// System.out.println("->->->->->->->->->->->->->->->->->->->->" +
			// cartonDto.getName());;

			cartonService.addCartonDto(cartonDto);
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateCarton(@Valid Principal principal, @RequestBody final CartonDto cartonDto) {
		if (CommonMethod.hasPrivilege(PrivilegeConstants.CARTON_UPDATE,
				userService.findUserByEmail(principal.getName()))) {
			cartonService.updateCartonDto(cartonDto);
			return new ResponseEntity<String>("Updated!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deleteCarton(@Valid Principal principal,

			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CARTON_DELETE,
				userService.findUserByEmail(principal.getName()))) {

			cartonService.delete(cartonService.findById(id));

			return new ResponseEntity<String>("Request Received", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> search(@Valid Principal principal,

			@Valid final String searchString) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.CARTON_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<List<CartonDto>>(cartonService.search(searchString), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

}