package com.sweetitech.sweeterp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sweetitech.sweeterp.repository.UsersRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
  	
    	
    	UserDetails ud = this.usersRepository.findByEmail(email);

        return ud;
    }
}