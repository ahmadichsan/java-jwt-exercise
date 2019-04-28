package com.app.employeemanagement.dto;

public class RoleDto {
	private String roleName;

	public RoleDto() {
		
	}
	
	public RoleDto(String roleName) {
		super();
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
