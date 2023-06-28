package com.seijo.flashCash.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(
                        (csrf)-> csrf.disable()
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/v1/auth/**", "/", "/static/bootstrap.min.css", "/static/index.css", "/images/**", "/signin", "signup")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(
                        (form)-> form
                                .loginPage("/")
                                .permitAll()
                                .usernameParameter("email")
                                .defaultSuccessUrl("/")
                )
                .logout(
                        (logout)-> logout
                                .permitAll()
                )
                .sessionManagement(
                        (session)-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
