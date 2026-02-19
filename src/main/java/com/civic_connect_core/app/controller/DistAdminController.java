package com.civic_connect_core.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminRequest;
import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminResponse;
import com.civic_connect_core.app.dtos.dist_admin_dtos.DistrictPublicResponse;
import com.civic_connect_core.app.services.DistrictAdminService;
import com.civic_connect_core.app.validation.email_validation.EmailValidation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/dist/admin")
public class DistAdminController {
    private final DistrictAdminService service;
    private final EmailValidation emailValidation;

    @GetMapping
    public ResponseEntity<DistAdminResponse> getDistAdminProfile() {
        return ResponseEntity.ok(service.getDistAdminProfile());
    }

    // create new district admin
    @PostMapping
    public ResponseEntity<?> insertDistAdmin(@Valid @RequestBody DistAdminRequest request) {
        if (emailValidation.isEmailExist(request.getAdminEmail())) {
            return ResponseEntity.badRequest().body(Map.of("Email", "Email Already Exist"));
        }
        if (service.isDistrictExist(request.getDistName())) {
            return ResponseEntity.badRequest().body(Map.of("District", "District Name Already Exist"));
        }
        return ResponseEntity.ok(service.insertDistAdmin(request));
    }

    @GetMapping("/public")
    public List<DistrictPublicResponse> getAllDistrictList(){
        return service.getAllDistrictList();
    }

}
