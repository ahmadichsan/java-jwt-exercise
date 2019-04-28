package com.app.employeemanagement.dto;

public class UserDto {

    private String username;
    private String password;
    private int age;
    private long salary;
    private Long role;
    
    public UserDto() {
    	
    }

	public UserDto(String username, String password, int age, long salary, Long role) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.salary = salary;
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Long getRole() {
		return role;
	}
	public void setRole(Long role) {
		this.role = role;
	}
}
