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
import com.sweetitech.sweeterp.model.dto.AreaDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.service.interfaces.IAreaService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/area")
public class AreaController {

	@Autowired
	private IAreaService areaService;

	@Autowired
	private IUserService userService;

	// view all areas
	@GetMapping("/")
	public @ResponseBody ResponseEntity<?> viewAllArea(Principal principal) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.AREA_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<AreaDto>>(areaService.findAllAreaDto(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view all areas by page
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<?> viewAllAreaByPage(Principal principal,
			@RequestParam(value = "pageId", required = true) int pageId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.AREA_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<AreaDto>>(areaService.findAllAreaDto(pageId), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}
	
	


	// view all areas by division page
	@GetMapping("/byDivision/page")
	public @ResponseBody ResponseEntity<?> viewAllAreaByDivisioByPage(Principal principal,
			@RequestParam(value = "pageId", required = false) Integer pageId,
			@RequestParam(value = "divisionId", required = true) Long divisionId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.AREA_VIEW, userService.findUserByEmail(principal.getName()))) {

			if (pageId == null) {
				pageId = 0;
			}
			return new ResponseEntity<Page<AreaDto>>(areaService.findAllAreaDtoByDivision(divisionId, pageId),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view by id
	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> viewAllAreaById(Principal principal,
			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.AREA_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<AreaDto>(areaService.findAreaDtoById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<?> addArea(@Valid Principal principal,

			@RequestBody @Valid final AreaDto areaDto) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.AREA_ADD, userService.findUserByEmail(principal.getName()))) {

			// System.out.println("->->->->->->->->->->->->->->->->->->->->" +
			// areaDto.getName());;

			areaService.addAreaDto(areaDto);
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateArea(@Valid Principal principal, @RequestBody final AreaDto areaDto) {
		if (CommonMethod.hasPrivilege(PrivilegeConstants.AREA_UPDATE,
				userService.findUserByEmail(principal.getName()))) {
			areaService.updateAreaDto(areaDto);
			return new ResponseEntity<String>("Updated!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deleteArea(@Valid Principal principal,

			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.AREA_DELETE,
				userService.findUserByEmail(principal.getName()))) {

			areaService.delete(areaService.findById(id));

			return new ResponseEntity<String>("Request Received", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> search(@Valid Principal principal,

			@Valid final String searchString) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.AREA_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<List<AreaDto>>(areaService.search(searchString), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	@RequestMapping(value = "/search/byDivision", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> search(@Valid Principal principal,
			@RequestParam(value = "divisionId", required = true) Long divisionId,
			@Valid final String searchString) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.AREA_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<List<AreaDto>>(areaService.searchByDivision(searchString, divisionId), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
}