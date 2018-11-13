package com.sweetitech.sweeterp.config;

import java.util.Iterator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.sweetitech.sweeterp.model.dto.TerritoryDto;

import com.sweetitech.sweeterp.model.user.Privilege;
import com.sweetitech.sweeterp.model.user.Role;
import com.sweetitech.sweeterp.model.user.Territory;
import com.sweetitech.sweeterp.model.user.User;

public class CommonMethod {
	
	@Autowired
	private static ModelMapper modelMapper1;
	
	public static boolean hasPrivilege(String privilege, User user) {

		boolean flag = false;

		Iterator<Role> roles = user.getRoles().iterator();
		while (roles.hasNext()) {
			Role r = roles.next();
			Iterator<Privilege> privileges = r.getPrivileges().iterator();
			while (privileges.hasNext()) {
				Privilege privilege1 = privileges.next();
				if (privilege1.getName().equals(privilege)) {
					//System.out.println(privilege1.getName());
					flag = true;
					break;
				}
			}
		}
		return flag;

	}
	
	
	
	public static TerritoryDto convertToTerritoryDto(Territory territory) {
		TerritoryDto territoryDto = modelMapper1.map(territory, TerritoryDto.class);
		return territoryDto;
	}
	
	
	public static Territory convertToTerritoryEntity(TerritoryDto territoryDto) {
		Territory territory = modelMapper1.map(territoryDto, Territory.class);
		return territory;
	}
}
