package com.deb.eccomerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deb.eccomerce.models.form.LoginForm;
import com.deb.eccomerce.repositories.PerfilRepo;
import com.deb.eccomerce.repositories.UsuarioRepo;
import com.deb.eccomerce.security.Token;
import com.deb.eccomerce.security.TokenService;

@RestController
@CrossOrigin("*")
public class LoginController {

  @Autowired
  private UsuarioRepo repository;
  @Autowired
  private PerfilRepo profileRepository;
  @Autowired
  private AuthenticationManager authManager;
  @Autowired
  TokenService tokenService;
  @Value("${auth.jwt.secret}")
  private String secret;

  @PostMapping("/login")
  private ResponseEntity<?> login(@RequestBody @Valid LoginForm form) {
    UsernamePasswordAuthenticationToken loginData = form.toUsernamePasswordAuthenticationToken();
    Authentication auth = authManager.authenticate(loginData);
    String token = tokenService.generateToken(auth);
    return ResponseEntity.ok(new Token(token));
  }
}
