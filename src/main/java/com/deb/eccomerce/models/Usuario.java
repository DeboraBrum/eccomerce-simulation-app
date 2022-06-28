package com.deb.eccomerce.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="usuarios")
public class Usuario implements UserDetails{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="us_id")
  private Long id;
  @Column(name="us_username")
  private String username;
  @Column(name="us_email")
  private String email;
  @Column(name="us_senha")
  private String senha;
  @ManyToOne
  @JoinColumn(name="us_perfil")
  private Perfil perfil;

  public Usuario(String username, String email, String senha, Perfil perfil){
    super();
    this.email = email;
    this.perfil = perfil;
    this.senha = senha;
    this.username = username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    ArrayList<Perfil> profilesList = new ArrayList<Perfil>();
		profilesList.add(this.perfil);
		return profilesList;
  }
  @Override
  public String getPassword() {
    return this.senha;
  }
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  @Override
  public boolean isEnabled() {
    return true;
  }
  
}
