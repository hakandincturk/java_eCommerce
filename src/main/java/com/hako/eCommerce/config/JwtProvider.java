package com.hako.eCommerce.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
  public String generateToken(Authentication authentication) {
    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    String jwt = Jwts.builder()
      .setIssuedAt(new Date())
      .setExpiration(new Date(new Date().getTime() + 1000 * 60 * 24))
      .claim("email", authentication.getName())
      .signWith(key)
      .compact();
      
    return jwt;
  }

  public String getEmailFromToken(String jwt) {
    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    Claims claims = Jwts.parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(jwt)
      .getBody();

    return String.valueOf(claims.get("email"));
  }
}
