package com.civic_connect_core.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.AuthDTO.AuthRequest;
import com.civic_connect_core.app.dtos.response.ApiResponse;
import com.civic_connect_core.app.dtos.response.LoginResponse;
import com.civic_connect_core.app.entity.Users;
import com.civic_connect_core.app.service.AuthService;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/v1/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody AuthRequest request) {
        var loginResponse = authService.login(request);
        var response = new ApiResponse<>(true, "Login Successfully", loginResponse, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody AuthRequest request) {
        var user = authService.register(request);
        return ResponseEntity.ok(user);
    }

}
