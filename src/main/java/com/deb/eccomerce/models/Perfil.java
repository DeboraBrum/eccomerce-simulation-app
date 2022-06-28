package com.deb.eccomerce.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="perfis")
@NoArgsConstructor
@Getter
@Setter
public class Perfil implements GrantedAuthority{

  @Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "perfil_id")
	private Integer id = 1;
	
	@Column(name = "perfil_nome")
	private String nome = "ADMIN";
	
	@OneToMany(mappedBy = "perfil")
	@JsonIgnoreProperties("perfil")
	private List<Usuario> userList;
  
  @Override
  public String getAuthority() {
    return this.nome;
  }
  
}
