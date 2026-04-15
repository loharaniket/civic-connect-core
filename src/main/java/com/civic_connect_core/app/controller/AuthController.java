package com.civic_connect_core.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.AuthDTO.AuthRequest;
import com.civic_connect_core.app.entity.Users;
import com.civic_connect_core.app.service.AuthService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/v1/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @GetMapping("/login")
    public String getMethodName() {
        return "Welcome to V1";
    }

    @PostMapping("/register")
    public ResponseEntity<Users> postMethodName(@RequestBody AuthRequest request) {
        var user = authService.register(request);
        return ResponseEntity.ok(user);
    }

}
