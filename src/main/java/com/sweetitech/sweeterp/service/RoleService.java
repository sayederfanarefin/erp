package com.sweetitech.sweeterp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sweetitech.sweeterp.model.dto.RoleAddDto;
import com.sweetitech.sweeterp.model.dto.RoleDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.user.Role;
import com.sweetitech.sweeterp.repository.RoleRepository;
import com.sweetitech.sweeterp.service.interfaces.IRoleService;

@Service
@Transactional
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public Role findById(long id) {
		return roleRepository.findById(id);
	}

	@Override
	public void delete(Role role) {
		roleRepository.delete(role);
	}

	@Override
	public Role updateRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Page<Role> findAllRole(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return roleRepository.findAll(request);
	}

	@Override
	public Role findByUsers_Id(Long userId) {
		return roleRepository.findByUsers_Id(userId);
	}

	@Override
	public RoleDto findRoleDtoByName(String name) {
		// TODO Auto-generated method stub
		return convertToDto(roleRepository.findByName(name));
	}

	@Override
	public RoleDto findRoleDtoById(Long id) {
		// TODO Auto-generated method stub
		return convertToDto(roleRepository.findById(id));
	}

	@Override
	public Page<RoleDto> findAllRoleDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Role> roles = roleRepository.findAll(request);

		Page<RoleDto> dtoPage = roles.map(new Converter<Role, RoleDto>() {
			@Override
			public RoleDto convert(Role entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public RoleDto addRoleDto(RoleAddDto roleDto) {
		// TODO Auto-generated method stub
		Role role = roleRepository.save(convertToEntity(roleDto));
		return convertToDto(role);
	}
	

	@Override
	public RoleDto updateRoleDto(RoleDto roleDto) {
		return convertToDto(roleRepository.save(convertToEntity(roleDto)));
	}

	@Override
	public void deleteRole(RoleDto roleDto) {
		roleRepository.delete(convertToEntity(roleDto));
	}
	
	
	private RoleDto convertToDto(Role role) {
		RoleDto roleDto = modelMapper.map(role, RoleDto.class);
		return roleDto;
	}
	
	private Role convertToEntity(RoleAddDto roleDto) {
		Role role = modelMapper.map(roleDto, Role.class);
		
		return role;
	}
	private Role convertToEntity(RoleDto roleDto) {
		Role role = modelMapper.map(roleDto, Role.class);
		return role;
	}
	
	
	@Override
	public List<RoleDto> search(String searchString) {
		List<Role> areas = roleRepository.findByNameContaining(searchString);
		List<RoleDto> areaDtos = new ArrayList<RoleDto>();
		for(int i=0; i < areas.size();i++) {
			RoleDto temp = convertToDto(areas.get(i));
			areaDtos.add(temp);
		}
		return areaDtos;
	}
}