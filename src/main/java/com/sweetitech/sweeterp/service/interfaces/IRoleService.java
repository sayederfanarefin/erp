package com.sweetitech.sweeterp.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.RoleAddDto;
import com.sweetitech.sweeterp.model.dto.RoleDto;

import com.sweetitech.sweeterp.model.user.Role;

public interface IRoleService {
	Role findByName(String name);
	Role findById(long id);
    void delete(Role role);
    Role updateRole(Role role);
    
    Page<Role> findAllRole( int page);
    Role findByUsers_Id(Long userId);
    
    
    RoleDto findRoleDtoByName(String name);
    RoleDto findRoleDtoById(Long id);
    Page<RoleDto> findAllRoleDto( int page);
    
    RoleDto addRoleDto(RoleAddDto roleDto);
    RoleDto updateRoleDto(RoleDto roleDto);
    void deleteRole(RoleDto roleDto);
    
    List<RoleDto> search(String searchString);
}
