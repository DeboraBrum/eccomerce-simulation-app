package com.deb.eccomerce.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.deb.eccomerce.models.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {
  @Value("${auth.jwt.expiration}")
	private String expiration;
	
	@Value("${auth.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authentication) {
		Usuario loggedAdmin = (Usuario) authentication.getPrincipal();
		Date today = new Date();
		Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

    byte[] keyBytes = Decoders.BASE64.decode(secret);
    Key key = Keys.hmacShaKeyFor(keyBytes);
		
		return Jwts.builder()
				.setIssuer("deboracorp")
				.setSubject(loggedAdmin.getId().toString())
				.setIssuedAt(today)
				.signWith(key, SignatureAlgorithm.HS256)
				.setExpiration(expirationDate)
				.compact();
	}
	
	public boolean isTokenValid(String token) {
		try {
			((JwtParser) Jwts.parserBuilder().setSigningKey(this.secret)).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Long getUserId (String token) {
		Claims claims = ((JwtParser) Jwts.parserBuilder().setSigningKey(this.secret)).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
