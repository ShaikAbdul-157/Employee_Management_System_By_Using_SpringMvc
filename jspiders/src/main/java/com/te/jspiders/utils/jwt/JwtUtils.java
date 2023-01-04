package com.te.jspiders.utils.jwt;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtils {
	private String secret = "some secret key";

	/* 1. Method for token generation! */
	public String generateToken(String subject) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(subject);
		builder.setIssuer("Abdul");
		builder.setIssuedAt(new Date(System.currentTimeMillis()));
		builder.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)));
		builder.signWith(SignatureAlgorithm.HS256, secret.getBytes());
		return builder.compact();
	}

	/* 2. Method for reading claims! */
	public Claims getClaims(String token) {
		JwtParser parser = Jwts.parser();
		parser.setSigningKey(secret.getBytes());
		Jws<Claims> parseClaimsJws = parser.parseClaimsJws(token);
		return parseClaimsJws.getBody();
	}

	/* 3. Method to get expiration date! */
	public Date getExpirationDate(String token) {
		return getClaims(token).getExpiration();
	}

	/* 4. Method to get subject user name! */
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	/* 5. Check if the token is expired! */
	public boolean isTokenExpired(String token) {
		Date expirationDate = getExpirationDate(token);
		return expirationDate.before(new Date(System.currentTimeMillis()));
	}

	/* 6. Method to validate a token! */
	public boolean validateToken(String token, String username) {
		String usernameFromToken = getUsername(token);
		return username.equals(usernameFromToken) && !isTokenExpired(token);
	}
}

