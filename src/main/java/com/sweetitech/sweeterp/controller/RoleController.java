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
import com.sweetitech.sweeterp.model.dto.RoleAddDto;
import com.sweetitech.sweeterp.model.dto.RoleDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.service.interfaces.IRoleService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserService userService;

	// view all roles
		@GetMapping("/")
		public @ResponseBody ResponseEntity<?> viewAllRole(Principal principal) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.ROLE_VIEW,
					userService.findUserByEmail(principal.getName()))) {
				
				return new ResponseEntity<Page<RoleDto>>(roleService.findAllRoleDto(0), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}

		// view all roles by page
		@GetMapping("/page")
		public @ResponseBody ResponseEntity<?> viewAllRoleByPage(Principal principal,
				@RequestParam(value = "pageId", required = true) int pageId) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.ROLE_VIEW,
					userService.findUserByEmail(principal.getName()))) {

				return new ResponseEntity<Page<RoleDto>>(roleService.findAllRoleDto(pageId), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}

		// view role by id
		@GetMapping("/id")
		public @ResponseBody ResponseEntity<?> viewAllRoleById(Principal principal,
				@RequestParam(value = "id", required = true) Long id) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.ROLE_VIEW,
					userService.findUserByEmail(principal.getName()))) {

				return new ResponseEntity<RoleDto>(roleService.findRoleDtoById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}
		
		
		@RequestMapping(value = "/add", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public ResponseEntity<?> addRole(
				@Valid Principal principal,
				
				@RequestBody @Valid final RoleAddDto roleDto) {
			
			if (CommonMethod.hasPrivilege(PrivilegeConstants.ROLE_ADD,
					userService.findUserByEmail(principal.getName()))) {
				
				//System.out.println("->->->->->->->->->->->->->->->->->->->->" + roleDto.getName());;

				roleService.addRoleDto(roleDto);
				return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}

			
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
		@ResponseBody
		public ResponseEntity<?> updateRole(
				@Valid Principal principal,
				@RequestBody final RoleDto roleDto) {
			if (CommonMethod.hasPrivilege(PrivilegeConstants.ROLE_UPDATE,
					userService.findUserByEmail(principal.getName()))) {
					roleService.updateRoleDto(roleDto);
				return new ResponseEntity<String>("Updated!", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}
		
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> deleteRole(
				@Valid Principal principal,
				
				@RequestParam(value = "id", required = true) Long id) {
			
			if (CommonMethod.hasPrivilege(PrivilegeConstants.ROLE_DELETE,
					userService.findUserByEmail(principal.getName()))) {

				roleService.delete(roleService.findById(id));
				
				return new ResponseEntity<String>("Request Received", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
			
		}
		
		
		@RequestMapping(value = "/search", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> search(@Valid Principal principal,

				@Valid final String searchString) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.ROLE_VIEW, userService.findUserByEmail(principal.getName()))) {

				return new ResponseEntity<List<RoleDto>>(roleService.search(searchString), HttpStatus.OK);
				
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}

		}
}
