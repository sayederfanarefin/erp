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
import com.sweetitech.sweeterp.model.dto.ProductCategoryDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.service.interfaces.IProductCategoryService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/productCategory")
public class ProductCategoryController {
	
	@Autowired
	private IProductCategoryService productCategoryService;

	@Autowired
	private IUserService userService;

	// view all products
	@GetMapping("/")
	public @ResponseBody ResponseEntity<?> viewAllProductCategory(Principal principal) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_CATEGORY_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<ProductCategoryDto>>(productCategoryService.findAllProductCategoryDto(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view all products by page
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<?> viewAllProductCategoryByPage(Principal principal,
			@RequestParam(value = "pageId", required = true) int pageId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_CATEGORY_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<ProductCategoryDto>>(productCategoryService.findAllProductCategoryDto(pageId), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	
	// view by id
	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> viewAllProductCategoryById(Principal principal,
			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_CATEGORY_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<ProductCategoryDto>(productCategoryService.findProductCategoryDtoById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<?> addProductCategory(@Valid Principal principal,

			@RequestBody @Valid final ProductCategoryDto areaDto) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_CATEGORY_ADD, userService.findUserByEmail(principal.getName()))) {

			// System.out.println("->->->->->->->->->->->->->->->->->->->->" +
			// areaDto.getName());;

			productCategoryService.addProductCategoryDto(areaDto);
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateProductCategory(@Valid Principal principal, @RequestBody final ProductCategoryDto areaDto) {
		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_CATEGORY_UPDATE,
				userService.findUserByEmail(principal.getName()))) {
			productCategoryService.updateProductCategoryDto(areaDto);
			return new ResponseEntity<String>("Updated!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deleteProductCategory(@Valid Principal principal,

			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_CATEGORY_DELETE,
				userService.findUserByEmail(principal.getName()))) {

			productCategoryService.delete(productCategoryService.findById(id));

			return new ResponseEntity<String>("Request Received", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

}
