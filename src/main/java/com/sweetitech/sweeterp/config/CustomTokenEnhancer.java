package com.sweetitech.sweeterp.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.sweetitech.sweeterp.model.user.Role;
import com.sweetitech.sweeterp.model.user.User;



public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    	
    	try {
	        User user = (User) authentication.getPrincipal();
	        final Map<String, Object> additionalInfo = new HashMap<>();
	        Long sb = (long) -1;
	        Iterator<Role> roles = user.getRoles().iterator();
			while (roles.hasNext()) {
				Role r = roles.next();
				sb = r.getId();
			}
	        additionalInfo.put("role", sb);
	        additionalInfo.put("email", user.getEmail());
	        
	        additionalInfo.put("expire_date", accessToken.getExpiration());
	        
	        additionalInfo.put("service", "sweet-erp-sales-module");
	        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
    		
    	}catch (Exception e) {
    		
    		
//    		String email = authentication.getUserAuthentication().getName();
//        	System.out.println(email);
//        	
//        	String rolee = "";
//        	Iterator i = authentication.getUserAuthentication().getAuthorities().iterator();
//        	while(i.hasNext()) {
//        		rolee = i.next().toString();
//        	}
//    		final Map<String, Object> additionalInfo = new HashMap<>();
//    		System.out.println(rolee);
//    		        additionalInfo.put("role", rolee);
//    		        additionalInfo.put("email", email);
//    		        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
	}

        return accessToken;
    }

}