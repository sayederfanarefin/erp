package com.sweetitech.sweeterp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.sweeterp.model.user.Division;

public interface DivisionRepository extends JpaRepository<Division, Long> {
	Division findById(Long id);
	List<Division> findByNameContaining(String searchString);
}
