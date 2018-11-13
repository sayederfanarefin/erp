package com.sweetitech.sweeterp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SweetErpSalesModuleApplication extends SpringBootServletInitializer {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {

		builder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SweetErpSalesModuleApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SweetErpSalesModuleApplication.class, args);
	}
}
