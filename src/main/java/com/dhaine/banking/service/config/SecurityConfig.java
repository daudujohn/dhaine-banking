package com.dhaine.banking.service.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Daudu John
 * @createdOn Mar-21(Sat)-2026
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  public static final String[] PATTERNS_TO_ALLOW = {
    "/documentation/**", "/create_account", "/login"
  };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers(PATTERNS_TO_ALLOW).permitAll().anyRequest().authenticated())
        .exceptionHandling(
            exceptions ->
                exceptions.authenticationEntryPoint(
                    (request, response, authException) ->
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")));

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
