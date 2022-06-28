package com.deb.eccomerce.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.deb.eccomerce.models.Usuario;
import com.deb.eccomerce.repositories.UsuarioRepo;

public class TokenBasedAuthFilter extends OncePerRequestFilter{

  private TokenService tokenService;
	private UsuarioRepo userRepository;
	
	public TokenBasedAuthFilter(TokenService tokenService, UsuarioRepo userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        String token = getToken(request);
        boolean validToken = tokenService.isTokenValid(token);
        if (validToken) {
          authenticateUser(token);
        }
        filterChain.doFilter(request, response);
    
  }

  private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
	
	private void authenticateUser (String token) {
		
		Long userId = tokenService.getUserId(token);
		Usuario user = userRepository.findById(userId).get();
		
		SecurityContextHolder
			.getContext()
			.setAuthentication(new UsernamePasswordAuthenticationToken(user.getId(), null, user.getAuthorities()));
	}
  
}
