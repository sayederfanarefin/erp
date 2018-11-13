package com.sweetitech.sweeterp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	 @Autowired
	 private AppConfig appConfig;
	
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                
                .antMatchers("/api/**").authenticated()
                .antMatchers("/registration").permitAll()
                .antMatchers("/sso").permitAll()
                .antMatchers("/connect").permitAll()
               
                .and().logout().logoutSuccessUrl("/").permitAll();
    }
       
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(appConfig.tokenStore());
    }
    
}