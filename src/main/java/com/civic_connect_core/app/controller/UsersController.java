package com.civic_connect_core.app.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.user_dtos.UserRequest;
import com.civic_connect_core.app.dtos.user_dtos.UserResponse;
import com.civic_connect_core.app.services.DistrictAdminService;
import com.civic_connect_core.app.services.UsersService;
import com.civic_connect_core.app.validation.email_validation.EmailValidation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UsersController {
    private final UsersService service;
    private final EmailValidation emailValidation;
    private final DistrictAdminService districtAdminService;

    @PostMapping
    public ResponseEntity<?> insertUser(@Valid @RequestBody UserRequest request) {
        if (emailValidation.isEmailExist(request.getUserEmail()))
            return ResponseEntity.badRequest().body(Map.of("Email", "Already Exist"));
        if (!districtAdminService.isDistIdPresent(request.getDistId())) {
            return ResponseEntity.badRequest().body(Map.of("District", "District Not Present"));
        }
        return ResponseEntity.ok(service.insertUser(request));
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserProfile() {
        return ResponseEntity.ok(service.getUserProfile());
    }

}
