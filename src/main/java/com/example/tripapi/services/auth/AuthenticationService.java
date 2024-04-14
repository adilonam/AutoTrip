package com.example.tripapi.services.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tripapi.dtos.AuthenticationRequest;
import com.example.tripapi.dtos.AuthenticationResponse;
import com.example.tripapi.dtos.LogoutRequest;
import com.example.tripapi.dtos.LogoutResponse;
import com.example.tripapi.dtos.RefreshAuthenticationRequest;
import com.example.tripapi.dtos.RegisterRequest;
import com.example.tripapi.models.auth.Token;
import com.example.tripapi.models.auth.TokenType;
import com.example.tripapi.models.auth.User;
import com.example.tripapi.repositories.auth.TokenRepository;
import com.example.tripapi.repositories.auth.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .build();
        
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
      }



      public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        var user = userRepository.findByEmail(request.getEmail())
            .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
      }



      public AuthenticationResponse refreshToken(
          RefreshAuthenticationRequest request
  )  {

  
   var refreshToken = request.getRefreshToken();
  var  userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.userRepository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
      return authResponse ;
      }
    }
    return null;
  }
  



  public LogoutResponse logout(
          LogoutRequest request
  )  {

  
   var accessToekn = request.getAccessToken();
  var  userEmail = jwtService.extractUsername(accessToekn);
  if (userEmail != null) {
    var user = this.userRepository.findByEmail(userEmail)
            .orElseThrow();
            revokeAllUserTokens(user);
  }
    return LogoutResponse.builder()
    .response("ok").build();
  }
  
  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }



  private void saveUserToken(User user, String jwtToken) {
    try {
        Token token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
            
        tokenRepository.save(token);
    } catch (DataIntegrityViolationException e) {
        // Handle the exception if a duplicate key error occurs
        // E.g., Log the error, throw a custom exception, etc.
        System.out.println("Error saving token: Token with the given key already exists.");
        // Consider re-throwing or handling it accordingly.
    }
 
}


}
