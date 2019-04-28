package com.app.employeemanagement.dto;

public class LoginResDto {
	private String username;
    private int age;
    private long salary;
    private RoleDto role;
    private String token;
    
    public LoginResDto() {
    	
    }
    
	public LoginResDto(String username, int age, long salary, RoleDto role, String token) {
		super();
		this.username = username;
		this.age = age;
		this.salary = salary;
		this.role = role;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
