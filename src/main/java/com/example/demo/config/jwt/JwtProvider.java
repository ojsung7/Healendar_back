package com.example.demo.config.jwt;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;

@Component
@Log
public class JwtProvider {
	
	@Value("$(jwt.secret)")
	private String jwtSecret;
		
	public String generateToken(String login, String role) {
		//Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
		//토근 유효기간설정 plusMinutes
		Date date = Date.from(ZonedDateTime.now().plusSeconds(10).toInstant());
		return Jwts.builder()
				.setSubject(login)
				.setAudience(role)
				.setExpiration(date)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			
		}
		return false;
	}
	
	public String getLoginFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}