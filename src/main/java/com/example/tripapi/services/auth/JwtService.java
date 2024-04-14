package com.example.tripapi.services.auth;

import java.security.Key;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.tripapi.repositories.auth.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.*;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;
  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;
  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;


  private final TokenRepository tokenRepository;

    public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
    }


    private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
   final Claims claims = extractAllClaims(token);
   return claimsResolver.apply(claims);
 }


 public String generateToken(UserDetails userDetails) {
   return generateToken(new HashMap<>(), userDetails);
 }


  public boolean isTokenValid(String token, UserDetails userDetails) {
    var isTokenValidRepository = tokenRepository.findByToken(token)
          .map(t -> !t.isExpired() && !t.isRevoked())
          .orElse(false);
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && isTokenValidRepository;
  }

  private boolean isTokenExpired(String token) {
   return extractExpiration(token).before(new Date());
 }


 private Date extractExpiration(String token) {
   return extractClaim(token, Claims::getExpiration);
 }


 public String generateToken(
  Map<String, Object> extraClaims,
  UserDetails userDetails
) {
return buildToken(extraClaims, userDetails, jwtExpiration);
}


public String generateRefreshToken(
  UserDetails userDetails
) {
return buildToken(new HashMap<>(), userDetails, refreshExpiration);
}


private String buildToken(
  Map<String, Object> extraClaims,
  UserDetails userDetails,
  long expiration
) {
return Jwts
    .builder()
    .setClaims(extraClaims)
    .setSubject(userDetails.getUsername())
    .setIssuedAt(new Date(System.currentTimeMillis()))
    .setExpiration(new Date(System.currentTimeMillis() + expiration))
    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
    .compact();
}


    private Key getSignInKey() {
       byte[] keyBytes = Decoders.BASE64.decode(secretKey);
       return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
