package com.sweetitech.sweeterp.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.PrivilegeDto;
import com.sweetitech.sweeterp.model.dto.PrivilegeDtoSimple;

import com.sweetitech.sweeterp.model.user.Privilege;
import com.sweetitech.sweeterp.model.user.User;
import com.sweetitech.sweeterp.supportingfiles.GroupPrivilege;

public interface IPrivilegeService {
	Privilege findByName(String name);
	Privilege findById(Long id);
    void delete(Privilege privilege);
    Privilege updatePrivilege(Privilege privilege);
    
    Page<Privilege> findAllPrivilege( int page);
    
    
    
    List<GroupPrivilege> findAllPrivileges();
    
    PrivilegeDto findPrivilegeDtoByName(String name);
    PrivilegeDto findPrivilegeDtoById(Long id);
    Page<PrivilegeDto> findAllPrivilegeDto( int page);
    List<PrivilegeDtoSimple> findAllPrivilegeDtoByUser(User user);
    
    List<GroupPrivilege> findAllPrivilegeDtoByUserByGroup(User user);
    
    
    List<PrivilegeDto> search(String searchString);
}
