package com.civic_connect_core.app.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(c -> c.ignoringRequestMatchers("/h2-console/*").disable())
                .authorizeHttpRequests(req -> req
                        .requestMatchers(HttpMethod.POST, "/api/dist/admin")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/user")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/dept/public")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/dist/admin/public")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/refresh")
                        .permitAll()
                        .requestMatchers("/api/issue/image/**")
                        .permitAll()
                        .requestMatchers("/api/dist/admin").hasRole("SUPER_ADMIN")
                        .requestMatchers("/api/dept").hasRole("SUPER_ADMIN")
                        .requestMatchers("/api/dept/admin").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers("/api/dept/admin/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers("/api/user").hasRole("USER")
                        .requestMatchers("/api/issue/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "USER")
                        .requestMatchers("/api/auth/me").hasAnyRole("SUPER_ADMIN", "ADMIN", "USER")
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
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://127.0.0.1:5500"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
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
