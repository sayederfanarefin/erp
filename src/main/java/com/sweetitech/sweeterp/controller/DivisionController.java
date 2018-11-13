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
import com.sweetitech.sweeterp.model.dto.DivisionDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.service.interfaces.IDivisionService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/division")
public class DivisionController {
	
	@Autowired
	private IDivisionService divisionService;

	@Autowired
	private IUserService userService;

	// view all divisions
		@GetMapping("/")
		public @ResponseBody ResponseEntity<?> viewAllDivision(Principal principal) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.DIVISION_VIEW,
					userService.findUserByEmail(principal.getName()))) {
				
				return new ResponseEntity<Page<DivisionDto>>(divisionService.findAllDivisionDto(0), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}

		// view all roles by page
		@GetMapping("/page")
		public @ResponseBody ResponseEntity<?> viewAllDivisionByPage(Principal principal,
				@RequestParam(value = "pageId", required = true) int pageId) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.DIVISION_VIEW,
					userService.findUserByEmail(principal.getName()))) {

				return new ResponseEntity<Page<DivisionDto>>(divisionService.findAllDivisionDto(pageId), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}

		// view role by id
		@GetMapping("/id")
		public @ResponseBody ResponseEntity<?> viewAllDivisionById(Principal principal,
				@RequestParam(value = "id", required = true) Long id) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.DIVISION_VIEW,
					userService.findUserByEmail(principal.getName()))) {

				return new ResponseEntity<DivisionDto>(divisionService.findDivisionDtoById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}
		
		
		@RequestMapping(value = "/add", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public ResponseEntity<?> addDivision(
				@Valid Principal principal,
				
				@RequestBody @Valid final DivisionDto divisionDto) {
			
			if (CommonMethod.hasPrivilege(PrivilegeConstants.DIVISION_ADD,
					userService.findUserByEmail(principal.getName()))) {
				
				//System.out.println("->->->->->->->->->->->->->->->->->->->->" + divisionDto.getName());;

				divisionService.addDivisionDto(divisionDto);
				return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}

			
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
		@ResponseBody
		public ResponseEntity<?> updateDivision(
				@Valid Principal principal,
				@RequestBody final DivisionDto divisionDto) {
			if (CommonMethod.hasPrivilege(PrivilegeConstants.DIVISION_UPDATE,
					userService.findUserByEmail(principal.getName()))) {
					divisionService.updateDivisionDto(divisionDto);
				return new ResponseEntity<String>("Updated!", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}
		
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> deleteDivision(
				@Valid Principal principal,
				
				@RequestParam(value = "id", required = true) Long id) {
			
			if (CommonMethod.hasPrivilege(PrivilegeConstants.DIVISION_DELETE,
					userService.findUserByEmail(principal.getName()))) {

				divisionService.delete(divisionService.findById(id));
				
				return new ResponseEntity<String>("Request Received", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
			
		}
		
		
		@RequestMapping(value = "/search", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> search(@Valid Principal principal,

				@Valid final String searchString) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.DIVISION_VIEW, userService.findUserByEmail(principal.getName()))) {

				return new ResponseEntity<List<DivisionDto>>(divisionService.search(searchString), HttpStatus.OK);
				
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}

		}
}