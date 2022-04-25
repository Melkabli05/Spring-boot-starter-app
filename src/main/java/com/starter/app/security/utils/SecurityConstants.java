package com.starter.app.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityConstants {

	// FIXME : Customize security constants for your application.


	public static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;


	public static final String SECRET_KEY = "mySecretKey";


	public static final String ISSUER = "com.starter.app";


	public static final String TOKEN_PREFIX = "Bearer ";


	public static final String HEADER_STRING = "Authorization";

	public static final String LOGIN_REQUEST_URI = "/login";

	public static final String REGISTRATION_REQUEST_URI = "/register";

	private SecurityConstants() {

		throw new UnsupportedOperationException();
	}


	public static String getAuthenticatedUsername() {

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return userDetails.getUsername();
	}

}
