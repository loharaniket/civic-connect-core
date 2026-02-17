package com.civic_connect_core.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.civic_connect_core.app.filters.JwtAuthenticationFilter;
import com.civic_connect_core.app.services.CustomUserDetailService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    // private final UserDetailsService userDetailsService;
    private final CustomUserDetailService customUserDetailService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(c -> c.ignoringRequestMatchers("/h2-console/*").disable())
                .authorizeHttpRequests(req -> req
                        .requestMatchers(HttpMethod.POST, "/api/dist/admin")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/user")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/refresh")
                        .permitAll()
                        .requestMatchers("/api/dist/admin").hasRole("SUPER_ADMIN")
                        .requestMatchers("/api/dept").hasRole("SUPER_ADMIN")
                        .requestMatchers("/api/dept/admin").hasRole("ADMIN")
                        .requestMatchers("/api/user").hasRole("USER")
                        .requestMatchers("/h2-console/**")
                        .permitAll())
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // (3) All
                        // .auow Frames
                )
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider(customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
