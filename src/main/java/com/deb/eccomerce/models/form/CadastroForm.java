package com.deb.eccomerce.models.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.deb.eccomerce.models.Perfil;
import com.deb.eccomerce.models.Usuario;
import com.deb.eccomerce.repositories.PerfilRepo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CadastroForm {
  @NotNull
  @NotEmpty
  private String username;

  @NotNull
  @NotEmpty
  private String email;

  @NotNull
  @NotEmpty
  private String senha;

  public CadastroForm(String name, String email, String password) {
    this.username = name;
    this.email = email;
    this.senha = new BCryptPasswordEncoder().encode(password);
  }

  public Usuario toUser(PerfilRepo profileRepository) {
    Perfil profile = profileRepository.findById(1).get();
    Usuario usuario = new Usuario(this.username, this.email, this.senha, profile);
    return usuario;
  }

}
