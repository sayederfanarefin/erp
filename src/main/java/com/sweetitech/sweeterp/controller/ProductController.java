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
import com.sweetitech.sweeterp.model.dto.ProductDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.service.interfaces.IProductService;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private IProductService productService;

	@Autowired
	private IUserService userService;

	// view all products
	@GetMapping("/")
	public @ResponseBody ResponseEntity<?> viewAllProduct(Principal principal) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<ProductDto>>(productService.findAllProductDto(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	// view all products by page
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<?> viewAllProductByPage(Principal principal,
			@RequestParam(value = "pageId", required = true) int pageId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<Page<ProductDto>>(productService.findAllProductDto(pageId), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	
	// view by id
	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> viewAllProductById(Principal principal,
			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<ProductDto>(productService.findProductDtoById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<?> addProduct(@Valid Principal principal,

			@RequestBody @Valid final ProductDto areaDto) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_ADD, userService.findUserByEmail(principal.getName()))) {

			// System.out.println("->->->->->->->->->->->->->->->->->->->->" +
			// areaDto.getName());;

			productService.addProductDto(areaDto);
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateProduct(@Valid Principal principal, @RequestBody final ProductDto areaDto) {
		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_UPDATE,
				userService.findUserByEmail(principal.getName()))) {
			productService.updateProductDto(areaDto);
			return new ResponseEntity<String>("Updated!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deleteProduct(@Valid Principal principal,

			@RequestParam(value = "id", required = true) Long id) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_DELETE,
				userService.findUserByEmail(principal.getName()))) {

			productService.delete(productService.findById(id));

			return new ResponseEntity<String>("Request Received", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> search(@Valid Principal principal,

			@Valid final String searchString) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.PRODUCT_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<List<ProductDto>>(productService.search(searchString), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

}
