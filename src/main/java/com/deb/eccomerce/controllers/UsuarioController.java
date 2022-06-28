package com.deb.eccomerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deb.eccomerce.models.Usuario;
import com.deb.eccomerce.models.form.CadastroForm;
import com.deb.eccomerce.repositories.PerfilRepo;
import com.deb.eccomerce.repositories.UsuarioRepo;

@RestController
@CrossOrigin("*")
public class UsuarioController {

  @Autowired
  private UsuarioRepo repo;
  @Autowired
  private PerfilRepo perfilRepo;

  @PostMapping("/cadastro")
  public ResponseEntity<?> cadastro(@RequestBody @Valid CadastroForm form) {
    Usuario usuarioByEmail = repo.findByEmail(form.getEmail());
    if (usuarioByEmail != null) {
      throw new RuntimeException("Email already in use.");
    }
    Usuario newUser = repo.save(form.toUser(perfilRepo));
    return ResponseEntity.ok(newUser);
  }
}
