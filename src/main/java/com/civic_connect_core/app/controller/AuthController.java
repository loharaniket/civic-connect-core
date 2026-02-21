package com.civic_connect_core.app.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.config.JwtConfig;
import com.civic_connect_core.app.dtos.auth.JwtResponse;
import com.civic_connect_core.app.dtos.auth.AuthRequest;
import com.civic_connect_core.app.services.CustomUserDetailService;
import com.civic_connect_core.app.services.JwtService;
import com.civic_connect_core.app.utility.SecurityContextDetail;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailService customUserDetailService;
    private final JwtConfig jwtConfig;
    private final SecurityContextDetail securityContextDetail;

    // @PostMapping
    // public ResponseEntity<JwtResponse> login(@Valid @RequestBody AuthRequest
    // request, HttpServletResponse response) {
    // System.out.println("request accept by login");
    // Authentication authentication = authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(request.getEmail(),
    // request.getPassword()));

    // if (authentication.isAuthenticated()) {
    // UserDetails userDetails =
    // customUserDetailService.loadUserByUsername(request.getEmail());
    // String accessToken = jwtService.generateAccessToken(userDetails);
    // String refreshToken = jwtService.generateRefreshToken(userDetails);
    // var cookie = new Cookie("refreshToken", refreshToken);
    // cookie.setHttpOnly(true);
    // cookie.setPath("/auth/refresh");
    // cookie.setMaxAge(jwtConfig.getRefreshToken());
    // cookie.setSecure(true);
    // response.addCookie(cookie);
    // return ResponseEntity.ok(new JwtResponse(accessToken));
    // }
    // return ResponseEntity.notFound().build();
    // }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getEmail());
            String accessToken = jwtService.generateAccessToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(userDetails);
            ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                    .httpOnly(true)
                    .secure(false) // local
                    .path("/")
                    .sameSite("Lax") // important
                    .maxAge(jwtConfig.getRefreshToken())
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.ok().body(Map.of("token", accessToken, "refreshToken", refreshToken));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@CookieValue(value = "refreshToken") String refreshToken) {
        if (!jwtService.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var email = jwtService.getEmailFromToken(refreshToken);
        var user = customUserDetailService.loadUserByUsername(email);
        String accessToken = jwtService.generateAccessToken(user);
        return ResponseEntity.ok(new JwtResponse(accessToken));

    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        String email = securityContextDetail.getEmailFromContext();
        var user = customUserDetailService.loadUserByUsername(email);
        return ResponseEntity.ok().body(Map.of("Role", user.getAuthorities()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialHandler() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
