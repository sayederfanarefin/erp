package com.sweetitech.sweeterp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetitech.sweeterp.model.user.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findById(Long id);
	
	
	List<User> findByEmployeeDetails_firstNameContaining(String searchString);
}
