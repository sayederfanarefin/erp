package com.sweetitech.sweeterp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    Role findById(Long id);
    @Override
    void delete(Role role);

    Role findByUsers_Id(Long userId);
    
    List<Role> findByNameContaining(String searchString);
}
