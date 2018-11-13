package com.sweetitech.sweeterp.supportingfiles;

import java.util.ArrayList;
import java.util.List;

import com.sweetitech.sweeterp.model.dto.PrivilegeDtoSUperSimple;

public class GroupPrivilege {

	String tag;
	List<PrivilegeDtoSUperSimple> privileges = new ArrayList<PrivilegeDtoSUperSimple>();
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public List<PrivilegeDtoSUperSimple> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<PrivilegeDtoSUperSimple> privileges) {
		this.privileges = privileges;
	}
	
	public void addPrivilege(PrivilegeDtoSUperSimple privilegeDto) {
		this.privileges.add(privilegeDto);
	}
}
