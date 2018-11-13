package com.sweetitech.sweeterp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sweetitech.sweeterp.model.user.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);
    void delete(Privilege privilege);
    
	Privilege findById(Long id);
    
    Page<Privilege> findAll(Pageable pageable);
    
    List<Privilege> findAllByTag(String tag);
    
    @Query("SELECT DISTINCT a.tag FROM Privilege a")
    List<String> findDistinctTag();
    
    
    List<Privilege> findByNameContaining(String searchString);
}
