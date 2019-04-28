package com.app.employeemanagement.controller;

import com.app.employeemanagement.config.TokenProvider;
import com.app.employeemanagement.dto.LoginResDto;
import com.app.employeemanagement.dto.RoleDto;
import com.app.employeemanagement.model.AuthToken;
import com.app.employeemanagement.model.LoginUser;
import com.app.employeemanagement.model.User;
import com.app.employeemanagement.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;
    
    @Autowired
    UserService userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser) throws AuthenticationException {
    	LoginResDto loginResDto = new LoginResDto();
    	User user = new User();
    	user = userService.findOne(loginUser.getUsername());
    	BeanUtils.copyProperties(user, loginResDto, "password");
    	RoleDto role = new RoleDto();
    	role.setRoleName(user.getRole().getRoleName());
    	loginResDto.setRole(role);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        AuthToken authToken = new AuthToken(token);
        loginResDto.setToken(authToken.getToken());
        return ResponseEntity.ok(loginResDto);
    }

}
