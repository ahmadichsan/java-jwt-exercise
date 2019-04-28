package com.app.employeemanagement.serviceImpl;

import com.app.employeemanagement.dao.UserDao;
import com.app.employeemanagement.model.Role;
import com.app.employeemanagement.model.User;
import com.app.employeemanagement.dto.UserDto;
import com.app.employeemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.*;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName()));
        return authorities;
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(Long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).get();
	}

	@Override
    public User save(UserDto user) throws Exception {
	    User newUser = new User();
	    BeanUtils.copyProperties(user, newUser, "password", "role");
	    Role role = new Role();
	    role.setId(user.getRole());
	    if (user.getRole().intValue() == 1) role.setRoleName("owner");
	    if (user.getRole().intValue() == 2) role.setRoleName("admin");
	    if (user.getRole().intValue() == 3) role.setRoleName("staff");
	    newUser.setRole(role);
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
	    try {
	    	userDao.save(newUser);
	    } catch (Exception e) {
	    	throw new Exception(e);
	    }
        return newUser;
    }
}
