package com.sweetitech.sweeterp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sweetitech.sweeterp.model.dto.PrivilegeDto;
import com.sweetitech.sweeterp.model.dto.PrivilegeDtoSUperSimple;
import com.sweetitech.sweeterp.model.dto.PrivilegeDtoSimple;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.user.Privilege;
import com.sweetitech.sweeterp.model.user.Role;
import com.sweetitech.sweeterp.model.user.User;
import com.sweetitech.sweeterp.repository.PrivilegeRepository;
import com.sweetitech.sweeterp.service.interfaces.IPrivilegeService;
import com.sweetitech.sweeterp.supportingfiles.GroupPrivilege;

@Service
@Transactional
public class PrivilegeService implements IPrivilegeService {

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Privilege findByName(String name) {

		return privilegeRepository.findByName(name);
	}

	@Override
	public Privilege findById(Long id) {
		return privilegeRepository.findById(id);
	}

	@Override
	public void delete(Privilege privilege) {
		privilegeRepository.delete(privilege);
	}

	@Override
	public Privilege updatePrivilege(Privilege privilege) {
		return privilegeRepository.save(privilege);
	}

	@Override
	public Page<Privilege> findAllPrivilege(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return privilegeRepository.findAll(request);
	}

	@Override
	public PrivilegeDto findPrivilegeDtoByName(String name) {
		return convertToDto(privilegeRepository.findByName(name));
	}

	@Override
	public PrivilegeDto findPrivilegeDtoById(Long id) {

		return convertToDto(privilegeRepository.findById(id));
	}

	@Override
	public Page<PrivilegeDto> findAllPrivilegeDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Privilege> privileges = privilegeRepository.findAll(request);

		Page<PrivilegeDto> dtoPage = privileges.map(new Converter<Privilege, PrivilegeDto>() {
			@Override
			public PrivilegeDto convert(Privilege entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	private PrivilegeDto convertToDto(Privilege privilege) {
		PrivilegeDto privilegeDto = modelMapper.map(privilege, PrivilegeDto.class);
		return privilegeDto;
	}
	
	private PrivilegeDtoSUperSimple convertToDtoPrivilegeDtoSUperSimple(Privilege privilege) {
		PrivilegeDtoSUperSimple privilegeDto = modelMapper.map(privilege, PrivilegeDtoSUperSimple.class);
		return privilegeDto;
	}

	private PrivilegeDtoSimple convertToPrivilegeDtoSimple(Privilege privilege) {
		PrivilegeDtoSimple privilegeDto = modelMapper.map(privilege, PrivilegeDtoSimple.class);
		return privilegeDto;
	}

	@Override
	public List<PrivilegeDtoSimple> findAllPrivilegeDtoByUser(User user) {

		List<PrivilegeDtoSimple> userPrivileges = new ArrayList<PrivilegeDtoSimple>();

		Iterator<Role> roles = user.getRoles().iterator();
		while (roles.hasNext()) {
			Role r = roles.next();
			Iterator<Privilege> privileges = r.getPrivileges().iterator();
			while (privileges.hasNext()) {
				userPrivileges.add(convertToPrivilegeDtoSimple(privileges.next()));

			}
		}

		return userPrivileges;
	}

	@Override
	public List<GroupPrivilege> findAllPrivileges() {

		List<GroupPrivilege> groupPrivilegeToBeReturned = new ArrayList<GroupPrivilege>();

		List<String> tags = privilegeRepository.findDistinctTag();

		for (int i = 0; i < tags.size(); i++) {
			GroupPrivilege groupPrivilege = new GroupPrivilege();
			groupPrivilege.setTag(tags.get(i));
			List<Privilege> privileges = privilegeRepository.findAllByTag(tags.get(i));
			for (int k = 0; k < privileges.size(); k++) {
				groupPrivilege.addPrivilege(convertToDtoPrivilegeDtoSUperSimple(privileges.get(k)));
			}

			groupPrivilegeToBeReturned.add(groupPrivilege);
		}

		return groupPrivilegeToBeReturned;
	}

	@Override
	public List<GroupPrivilege> findAllPrivilegeDtoByUserByGroup(User user) {
		List<Privilege> userPrivileges = new ArrayList<Privilege>();

		List<GroupPrivilege> groupPrivilegeToBeReturned = new ArrayList<GroupPrivilege>();

		
		Iterator<Role> roles = user.getRoles().iterator();
		while (roles.hasNext()) {
			Role r = roles.next();
			Iterator<Privilege> privileges = r.getPrivileges().iterator();
			while (privileges.hasNext()) {
				userPrivileges.add(privileges.next());

			}
		}
		
		for (int i = 0; i < userPrivileges.size(); i++) {
			
			boolean flag = false;
			int idx = -1;
			for(int l=0;l < groupPrivilegeToBeReturned.size();l++) {
				GroupPrivilege groupPrivilege = groupPrivilegeToBeReturned.get(l);
				
				if(groupPrivilege.getTag().equals(userPrivileges.get(i).getTag())) {
					flag = true;
					idx = l;
				}
			}
			
			if(flag) {
				GroupPrivilege groupPrivilege = groupPrivilegeToBeReturned.get(idx);
				
				PrivilegeDtoSUperSimple a = convertToDtoPrivilegeDtoSUperSimple(userPrivileges.get(i));
				a.setName(a.getName().split("_")[1]);
				groupPrivilege.addPrivilege(a);
				
				groupPrivilegeToBeReturned.set(idx, groupPrivilege);
			}else {
				GroupPrivilege groupPrivilege = new GroupPrivilege();
				groupPrivilege.setTag(userPrivileges.get(i).getTag());
				
				PrivilegeDtoSUperSimple a = convertToDtoPrivilegeDtoSUperSimple(userPrivileges.get(i));
				
				a.setName(a.getName().split("_")[1]);
				groupPrivilege.addPrivilege(a);
				
				//System.out.println(groupPrivilege.getTag());
				
				groupPrivilegeToBeReturned.add(groupPrivilege);
			}
			
		}
		
		return groupPrivilegeToBeReturned;

	}
	
	@Override
	public List<PrivilegeDto> search(String searchString) {
		List<Privilege> areas = privilegeRepository.findByNameContaining(searchString);
		List<PrivilegeDto> areaDtos = new ArrayList<PrivilegeDto>();
		for(int i=0; i < areas.size();i++) {
			PrivilegeDto temp = convertToDto(areas.get(i));
			areaDtos.add(temp);
		}
		return areaDtos;
	}

}