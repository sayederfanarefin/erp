package com.sweetitech.sweeterp.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.sweetitech.sweeterp.model.dto.PrivilegeDto;
import com.sweetitech.sweeterp.model.dto.PrivilegeDtoSimple;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.service.interfaces.IPrivilegeService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;
import com.sweetitech.sweeterp.supportingfiles.GroupPrivilege;

@RestController
@RequestMapping("/api/privilege")
public class PrivilegeController {

	@Autowired
	private IPrivilegeService privilegeService;

	@Autowired
	private IUserService userService;

	// view all privileges
	@GetMapping("/")
	public @ResponseBody ResponseEntity<?> viewAllPrivilege(Principal principal) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRIVILEGE_VIEW,
				userService.findUserByEmail(principal.getName()))) {
			
			return new ResponseEntity<Page<PrivilegeDto>>(privilegeService.findAllPrivilegeDto(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}
	
	// view all privileges
		@GetMapping("/group")
		public @ResponseBody ResponseEntity<?> viewAllPrivilegeByGroup(Principal principal) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.PRIVILEGE_VIEW,
					userService.findUserByEmail(principal.getName()))) {
				
				return new ResponseEntity<List<GroupPrivilege>>(privilegeService.findAllPrivileges(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}

	// view all privileges by page
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<?> viewAllPrivilegeByPage(Principal principal,
			@RequestParam(value = "pageId", required = true) int pageId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRIVILEGE_VIEW,
				userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<PrivilegeDto>>(privilegeService.findAllPrivilegeDto(pageId), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view privilege by id
	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> viewAllPrivilegeById(Principal principal,
			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRIVILEGE_VIEW,
				userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<PrivilegeDto>(privilegeService.findPrivilegeDtoById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}
	
	
	
		@GetMapping("/myPrivileges")
		public @ResponseBody ResponseEntity<?> viewAllPrivilegeByRole(Principal principal) {

				return new ResponseEntity<List<PrivilegeDtoSimple>>(privilegeService.findAllPrivilegeDtoByUser(userService.findUserByEmail(principal.getName())), HttpStatus.OK);
			
		}
		
		@GetMapping("/myPrivileges/group")
		public @ResponseBody ResponseEntity<?> viewAllPrivilegeByRoleByGroup(Principal principal) {

				return new ResponseEntity<List<GroupPrivilege>>(privilegeService.findAllPrivilegeDtoByUserByGroup(userService.findUserByEmail(principal.getName())), HttpStatus.OK);
			
		}

		
		@RequestMapping(value = "/search", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> search(@Valid Principal principal,

				@Valid final String searchString) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.PRIVILEGE_VIEW, userService.findUserByEmail(principal.getName()))) {

				return new ResponseEntity<List<PrivilegeDto>>(privilegeService.search(searchString), HttpStatus.OK);
				
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}

		}

}
