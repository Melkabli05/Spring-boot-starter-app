package com.starter.app.security.jwt;

import com.starter.app.model.User;
import com.starter.app.model.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.starter.app.security.utils.SecurityConstants.*;


@Component
public class JwtTokenManager {

	// FIXME : Customize JWT token management for your application

	public String generateToken(User user) {

		final String username = user.getUsername();
		final UserRole userRole = user.getUserRole();

		final Claims claims = Jwts.claims().setSubject(username);
		claims.put("role", userRole.name());

		final long currentTimeMillis = System.currentTimeMillis();

		return Jwts.builder()
				.setClaims(claims)
				.setIssuer(ISSUER)
				.setIssuedAt(new Date(currentTimeMillis))
				.setExpiration(new Date(currentTimeMillis + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}

	public String getUsernameFromToken(String token) {

		final Claims claims = getAllClaimsFromToken(token);
		return claims.getSubject();
	}

	public boolean validateToken(String token, String authenticatedUsername) {

		final String usernameFromToken = getUsernameFromToken(token);

		final boolean equalsUsername = usernameFromToken.equals(authenticatedUsername);
		final boolean tokenExpired = isTokenExpired(token);

		return equalsUsername && !tokenExpired;
	}

	private boolean isTokenExpired(String token) {

		final Date expirationDateFromToken = getExpirationDateFromToken(token);
		return expirationDateFromToken.before(new Date());
	}

	private Date getExpirationDateFromToken(String token) {

		final Claims claims = getAllClaimsFromToken(token);
		return claims.getExpiration();
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

}
