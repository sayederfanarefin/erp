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
import com.sweetitech.sweeterp.model.dto.TerritoryDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.service.interfaces.ITerritoryService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/territory")
public class TerritoryController {
	
	@Autowired
	private ITerritoryService territoryService;

	@Autowired
	private IUserService userService;

	// view all territorys
		@GetMapping("/")
		public @ResponseBody ResponseEntity<?> viewAllTerritory(Principal principal) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.TERRITORY_VIEW,
					userService.findUserByEmail(principal.getName()))) {
				
				return new ResponseEntity<Page<TerritoryDto>>(territoryService.findAllTerritoryDto(0), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}

		// view all roles by page
		@GetMapping("/page")
		public @ResponseBody ResponseEntity<?> viewAllTerritoryByPage(Principal principal,
				@RequestParam(value = "pageId", required = true) int pageId) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.TERRITORY_VIEW,
					userService.findUserByEmail(principal.getName()))) {

				return new ResponseEntity<Page<TerritoryDto>>(territoryService.findAllTerritoryDto(pageId), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}

		// view role by id
		@GetMapping("/id")
		public @ResponseBody ResponseEntity<?> viewAllTerritoryById(Principal principal,
				@RequestParam(value = "id", required = true) Long id) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.TERRITORY_VIEW,
					userService.findUserByEmail(principal.getName()))) {

				return new ResponseEntity<TerritoryDto>(territoryService.findTerritoryDtoById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}
		
		
		@RequestMapping(value = "/add", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public ResponseEntity<?> addTerritory(
				@Valid Principal principal,
				
				@RequestBody @Valid final TerritoryDto territoryDto) {
			
			if (CommonMethod.hasPrivilege(PrivilegeConstants.TERRITORY_ADD,
					userService.findUserByEmail(principal.getName()))) {
				
				//System.out.println("->->->->->->->->->->->->->->->->->->->->" + territoryDto.getName());;

				territoryService.addTerritoryDto(territoryDto);
				return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}

			
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
		@ResponseBody
		public ResponseEntity<?> updateTerritory(
				@Valid Principal principal,
				@RequestBody final TerritoryDto territoryDto) {
			if (CommonMethod.hasPrivilege(PrivilegeConstants.TERRITORY_UPDATE,
					userService.findUserByEmail(principal.getName()))) {
					territoryService.updateTerritoryDto(territoryDto);
				return new ResponseEntity<String>("Updated!", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}
		
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> deleteTerritory(
				@Valid Principal principal,
				
				@RequestParam(value = "id", required = true) Long id) {
			
			if (CommonMethod.hasPrivilege(PrivilegeConstants.TERRITORY_DELETE,
					userService.findUserByEmail(principal.getName()))) {

				territoryService.delete(territoryService.findById(id));
				
				return new ResponseEntity<String>("Request Received", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
			
		}
		
		// view all areas by division page
		@GetMapping("/byArea/page")
		public @ResponseBody ResponseEntity<?> viewAllTerritoryByAreaByPage(Principal principal,
				@RequestParam(value = "pageId", required = false) Integer pageId,
				@RequestParam(value = "areaId", required = true) Long areaId) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.TERRITORY_VIEW, userService.findUserByEmail(principal.getName()))) {

				if (pageId == null) {
					pageId = 0;
				}
				return new ResponseEntity<Page<TerritoryDto>>(territoryService.findAllTerritoryDtoByArea(areaId, pageId),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}
		}
		
		
		@RequestMapping(value = "/search", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> search(@Valid Principal principal,

				@Valid final String searchString) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.TERRITORY_VIEW, userService.findUserByEmail(principal.getName()))) {

				return new ResponseEntity<List<TerritoryDto>>(territoryService.search(searchString), HttpStatus.OK);
				
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}

		}
		
		
		@RequestMapping(value = "/search/byArea", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> search(@Valid Principal principal,
				@RequestParam(value = "areaId", required = true) Long areaId,
				@Valid final String searchString) {

			if (CommonMethod.hasPrivilege(PrivilegeConstants.AREA_VIEW, userService.findUserByEmail(principal.getName()))) {

				return new ResponseEntity<List<TerritoryDto>>(territoryService.searchByArea(searchString, areaId), HttpStatus.OK);
				
			} else {
				return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
			}

		}

}