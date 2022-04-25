package com.starter.app.security.service;

import com.starter.app.model.User;
import com.starter.app.security.dto.AuthenticatedUserDto;
import com.starter.app.security.dto.RegistrationRequest;
import com.starter.app.security.dto.RegistrationResponse;


public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
