package com.app.employeemanagement.service;

import com.app.employeemanagement.model.User;
import com.app.employeemanagement.dto.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user) throws Exception;

    List<User> findAll();

    void delete(Long id);

    User findOne(String username);

    User findById(Long id);
}
