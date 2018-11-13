package com.sweetitech.sweeterp.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
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
import com.sweetitech.sweeterp.model.dto.UserDto;

import com.sweetitech.sweeterp.config.CommonMethod;
import com.sweetitech.sweeterp.config.PrivilegeConstants;
import com.sweetitech.sweeterp.model.user.User;
import com.sweetitech.sweeterp.service.interfaces.IUserService;
//import com.sweetitech.tiger.service.interfaces.IImageService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private ModelMapper modelMapper;


	@Autowired
	private IUserService userService;

	@GetMapping("/email")
	public String userName(Principal principal) {
		String a = principal.getName();
		return a;
	}

	@GetMapping("/")
	public UserDto user(Principal principal) {
		String a = principal.getName();
		UserDto u =  userService.findUserProfileDtoByEmail(a);
		u.setPassword(null);
		return u;
	}

//	@GetMapping("/upload/profile-picture")
//	public ResponseEntity<?> uploadProfilePicture(Principal principal,
//			@RequestParam(value = "imageId", required = true) long imageId) {
//		String a = principal.getName();
//
//		Image newProfilePicture = imageService.findById(imageId);
//
//		User user = userService.findUserByEmail(a);
//
//		user.setProfilePicture(newProfilePicture);
//
//		userService.saveRegisteredUser(user);
//
//		// imageService.deleteImage(user.getProfilePicture());
//
//		return new ResponseEntity("Profile Picture updated", HttpStatus.OK);
//
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity<?> updateUser(@Valid Principal principal,
//			@RequestParam(value = "firstName", required = false) String firstName,
//			@RequestParam(value = "lastName", required = false) String lastName,
//			@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
//			@RequestParam(value = "profilePicture", required = false) Long profilePicture,
//			@RequestParam(value = "email", required = false) String email
//
//	) {
//
//		User userTobeUpdated = userService.findUserByEmail(principal.getName());
//
//		if (firstName != null && firstName != "") {
//			userTobeUpdated.setFirstName(firstName);
//		}
//
//		if (lastName != null && lastName != "") {
//			userTobeUpdated.setLastName(lastName);
//		}
//
//		if (phoneNumber != null && phoneNumber != "") {
//			userTobeUpdated.setPhoneNumber(phoneNumber);
//		}
//
//		if (email != null && email != "") {
//			userTobeUpdated.setEmail(email);
//		}
//
//		if (profilePicture != null && profilePicture != 0) {
//			userTobeUpdated.setProfilePicture(imageService.findById(profilePicture));
//		}
//
//		
//
//		userService.saveRegisteredUser(userTobeUpdated);
//		return new ResponseEntity("Profile updated!", HttpStatus.OK);
//
//	}
//	
//
	@GetMapping("/showAllUsers")
	public @ResponseBody ResponseEntity<Page<UserDto>> showAllUsers(Principal principal) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.USER_VIEW, userService.findUserByEmail(principal.getName()))) {
			//Page<UserProfileDto> usersList = userService.findAllUser(0);

			Page<User> usersListPage = userService.findAllUser(0);

			
			Page<UserDto> pp =   usersListPage.map(new Converter<User, UserDto>() {
			    @Override
			    public UserDto convert(User entity) {
			    //	UserProfileDto dto = new UserProfileDto();
			        // Conversion logic

			        return convertToDto(entity);
			    }
			});  //new PageImpl<UserProfileDto>(userProfileDtoList);
			
			return new ResponseEntity(pp, HttpStatus.OK);
		} else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	@GetMapping("/showAllUsers/page")
	public @ResponseBody ResponseEntity<Page<UserDto>> showAllUsersByPage(Principal principal, @RequestParam(value = "pageId", required = true) int pageId) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.USER_VIEW, userService.findUserByEmail(principal.getName()))) {
			//Page<UserProfileDto> usersList = userService.findAllUser(0);

			Page<User> usersListPage = userService.findAllUser( pageId);

			
			Page<UserDto> pp =   usersListPage.map(new Converter<User, UserDto>() {
			    @Override
			    public UserDto convert(User entity) {
			    //	UserProfileDto dto = new UserProfileDto();
			        // Conversion logic

			        return convertToDto(entity);
			    }
			});  //new PageImpl<UserProfileDto>(userProfileDtoList);
			
			return new ResponseEntity(pp, HttpStatus.OK);
		} else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}


	
	@RequestMapping(value = "/add", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<?> addRole(
			@Valid Principal principal,
			
			@RequestBody final UserDto accountDto) {
		
		if (CommonMethod.hasPrivilege(PrivilegeConstants.USER_ADD,
				userService.findUserByEmail(principal.getName()))) {
			
			//System.out.println("->->->->->->->->->->->->->->->->->->->->" + accountDto.getEmail());

			User u = userService.registerNewUserAccount(accountDto);
			if(u==null) {
				return new ResponseEntity<String>("Something went wrong, please try again later.", HttpStatus.FORBIDDEN);
			}else {
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
			}
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
//	private boolean hasPrivilege(String privilege, User user) {
//
//		boolean flag = false;
//
//		Iterator<Role> roles = user.getRoles().iterator();
//		while (roles.hasNext()) {
//			Role r = roles.next();
//			Iterator<Privilege> privileges = r.getPrivileges().iterator();
//			while (privileges.hasNext()) {
//				Privilege privilege1 = privileges.next();
//				if (privilege1.getName().equals(privilege)) {
//					flag = true;
//					break;
//				}
//			}
//		}
//		return flag;
//
//	}
	
	
//	private UserProfileDto convertToDto(User user) {
//		UserProfileDto userProfileDto = modelMapper.map(user, UserProfileDto.class);
//		String sb = "";
//        Iterator<Role> roles = user.getRoles().iterator();
//		while (roles.hasNext()) {
//			Role r = roles.next();
//			sb = r.getName();
//		}
//		userProfileDto.setRole(sb);
//		
//	    return userProfileDto;
//	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> search(@Valid Principal principal,

			@Valid final String searchString) {

		if (CommonMethod.hasPrivilege(PrivilegeConstants.AREA_VIEW, userService.findUserByEmail(principal.getName()))) {

			return new ResponseEntity<List<UserDto>>(userService.search(searchString), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	private UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
		return userDto;
	}
	
}
