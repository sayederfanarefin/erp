package com.sweetitech.sweeterp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.user.Territory;

public interface TerritoryRepository extends JpaRepository<Territory, Long> {
	Territory findAreaById(Long id);
	Page<Territory> findAllByArea_Id(Long id, Pageable pageable);
	Territory findById(Long id);
	Territory save(Territory territory);
	
	List<Territory> findByNameContaining(String searchString);
	

	List<Territory> findByNameContainingAndArea_Id(String searchString, Long areaId);
}
