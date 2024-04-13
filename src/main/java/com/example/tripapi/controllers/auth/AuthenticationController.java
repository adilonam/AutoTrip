package com.example.tripapi.controllers.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tripapi.dtos.AuthenticationRequest;
import com.example.tripapi.dtos.AuthenticationResponse;
import com.example.tripapi.dtos.RefreshAuthenticationRequest;
import com.example.tripapi.dtos.RegisterRequest;
import com.example.tripapi.services.auth.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(authenticationService.register(request));
  }


  @PostMapping("/refresh-token")
  public ResponseEntity<AuthenticationResponse>  refreshToken(
    @RequestBody RefreshAuthenticationRequest request
  )  {
   return ResponseEntity.ok(authenticationService.refreshToken(request));
  }
  
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
 
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }

 


}