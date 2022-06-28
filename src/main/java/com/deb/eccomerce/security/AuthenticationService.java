package com.deb.eccomerce.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deb.eccomerce.models.Usuario;
import com.deb.eccomerce.repositories.UsuarioRepo;

@Service
public class AuthenticationService implements UserDetailsService {

  @Autowired
  UsuarioRepo repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario user = repository.findByEmail(username);
    if (user != null) {
      return user;
    }
    throw new UsernameNotFoundException("Invalid username.");
  }
}
