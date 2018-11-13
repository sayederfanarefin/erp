package com.sweetitech.sweeterp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.user.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {
	
	Page<Area> findAllByDivision_Id(Long id, Pageable pageable);
	Area findById(Long id);
	
	List<Area> findByNameContaining(String searchString);
	
	
	List<Area> findByNameContainingAndDivision_Id(String searchString, Long divisionId);
}
