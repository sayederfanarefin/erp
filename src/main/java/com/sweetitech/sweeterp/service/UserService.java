package com.sweetitech.sweeterp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sweetitech.sweeterp.model.dto.TerritoryDto;
import com.sweetitech.sweeterp.model.dto.UserDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.exception.UserAlreadyExistException;
import com.sweetitech.sweeterp.model.user.Territory;
import com.sweetitech.sweeterp.model.user.User;
import com.sweetitech.sweeterp.repository.RoleRepository;
import com.sweetitech.sweeterp.repository.TerritoryRepository;
import com.sweetitech.sweeterp.repository.UsersRepository;
import com.sweetitech.sweeterp.service.interfaces.IUserService;

@Service
@Transactional
public class UserService implements IUserService {
	
	

	@Autowired
	private UsersRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private TerritoryRepository territoryRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static final String TOKEN_INVALID = "invalidToken";
	public static final String TOKEN_EXPIRED = "expired";
	public static final String TOKEN_VALID = "valid";

	@Override
	public User registerNewUserAccount(final UserDto accountDto) {
		if (emailExist(accountDto.getEmail())) {
			throw new UserAlreadyExistException("There is an account with this email adress: " + accountDto.getEmail());
		}

		
		User u = convertToEntity(accountDto);

		for (int i = 0; i < accountDto.getTerritories().size(); i++) {
			Territory t = convertToTerritoryEntity(accountDto.getTerritories().get(i));
			 u.setTerritory(t);
		}
		u.setPassword(passwordEncoder.encode(accountDto.getPassword()));
	
		
		return repository.save(u);
	}

	@Override
	public void saveRegisteredUser(final User user) {
		repository.save(user);
	}

	@Override
	public User findUserByEmail(final String email) {
		return repository.findByEmail(email);
	}

	@Override
	public User getUserByID(final long id) {
		return repository.findById(id);
	}

	@Override
	public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
		return false;// passwordEncoder.matches(oldPassword, user.getPassword());
	}

	private boolean emailExist(final String email) {
		return repository.findByEmail(email) != null;
	}

	@Override
	public UserDto findUserProfileDtoByEmail(String email) {

		User u = repository.findByEmail(email);
		return convertToDto(u);
	}

	@Override
	public Page<User> findAllUser(int page) {

		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return repository.findAll(request);

	}

	private UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
		return userDto;
	}

	private User convertToEntity(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		return user;
	}

	public Territory convertToTerritoryEntity(TerritoryDto territoryDto) {
		Territory territory = modelMapper.map(territoryDto, Territory.class);
		return territory;
	}
	
	
	@Override
	public List<UserDto> search(String searchString) {
		List<User> areas = repository.findByEmployeeDetails_firstNameContaining(searchString);
		List<UserDto> areaDtos = new ArrayList<UserDto>();
		for(int i=0; i < areas.size();i++) {
			UserDto temp = convertToDto(areas.get(i));
			areaDtos.add(temp);
		}
		return areaDtos;
	}

}