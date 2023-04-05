package com.abi.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	public static final String ISSUER = "Abilash";
	public static final long EXPIRE_MS = 60 * 60 * 1000;
	private static final String SECRET_KEY = "uzsfawsjksnkdjs8d3wgehghcfyuaeyd";
	private static final String ROLE = "role";

	public boolean isValidToken(String token) {
		return isNotExpired(token);
	}

	private boolean isNotExpired(String token) {
		// TODO Auto-generated method stub
		return getExpireyDate(token).after(new Date());
	}

	private Date getExpireyDate(String token) {
		// TODO Auto-generated method stub
		return getDataFromToken(token, Claims::getExpiration);
	}

	public String getUserNameFromToken(String token) {
		// TODO Auto-generated method stub
		return getDataFromToken(token, Claims::getSubject);
	}
	
	public String getRoleFromToken(String token) {
		// TODO Auto-generated method stub
		return (String) getClaims(token).get(ROLE);
	}

	private <T> T getDataFromToken(String token, Function<Claims, T> claimResolver) {
		Claims claim = getClaims(token);
		return claimResolver.apply(claim);
	}

	private Claims getClaims(String token) {
		// TODO Auto-generated method stub
		return (Claims) Jwts.parser().setSigningKey(SECRET_KEY).parse(token).getBody();
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> roleMap=new HashMap<>();
		for(GrantedAuthority grandAuthourity:userDetails.getAuthorities()) {
			roleMap.put(ROLE, grandAuthourity.getAuthority());
		}
		// TODO Auto-generated method stub
		return Jwts.builder().setSubject(userDetails.getUsername())
				.setExpiration(new Date(new Date().getTime() + EXPIRE_MS)).setIssuedAt(new Date()).setIssuer(ISSUER)
				.addClaims(roleMap)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
}
