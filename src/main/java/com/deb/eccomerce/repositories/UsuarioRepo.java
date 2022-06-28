package com.deb.eccomerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deb.eccomerce.models.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
  public Usuario findByEmail(String email);
}
