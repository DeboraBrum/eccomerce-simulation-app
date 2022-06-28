package com.deb.eccomerce.models.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginForm {
  @NotNull @NotEmpty
	private String username;
	
	@NotNull @NotEmpty
	private String senha;

	public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken () {
		return new UsernamePasswordAuthenticationToken(this.username, this.senha);
	}
}
