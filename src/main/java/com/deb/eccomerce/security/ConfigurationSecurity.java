package com.deb.eccomerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.deb.eccomerce.repositories.UsuarioRepo;

@EnableWebSecurity
@Configuration
public class ConfigurationSecurity {

  @Autowired
  private AuthenticationService authService;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UsuarioRepo repository;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.POST, "/cadastro").permitAll()
        .anyRequest().authenticated()
        .and().cors()
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new TokenBasedAuthFilter(tokenService, repository),
            UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring()
        .antMatchers(HttpMethod.GET, "/v2/api-docs", "/configuration/ui",
            "/swagger-resources/**", "/configuration/security", "/swagger-ui/*", "/webjars/**");
  }

  // Deprecated
  // @Override
  // @Bean
  // protected AuthenticationManager authenticationManager() throws Exception {
  // return super.authenticationManager();
  // }
  // @Override
  // protected void configure(AuthenticationManagerBuilder auth) throws Exception
  // {
  // auth.userDetailsService(authService)
  // .passwordEncoder(new BCryptPasswordEncoder());
  // }
  // @Override
  // protected void configure(HttpSecurity http) throws Exception {
  // http.authorizeRequests()
  // .antMatchers(HttpMethod.POST, "/login").permitAll()
  // .anyRequest().authenticated()
  // .and().cors()
  // .and().csrf().disable()
  // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
  // .and().addFilterBefore(new TokenBasedAuthFilter(tokenService, repository),
  // UsernamePasswordAuthenticationFilter.class);
  // }
  // @Override
  // public void configure(WebSecurity web) throws Exception {
  // web.ignoring()
  // .antMatchers(HttpMethod.GET, "/v2/api-docs", "/configuration/ui",
  // "/swagger-resources/**", "/configuration/security", "/swagger-ui/*",
  // "/webjars/**");
  // }

}
