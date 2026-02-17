package com.civic_connect_core.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminRequest;
import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminResponse;
import com.civic_connect_core.app.services.DeptAdminService;
import com.civic_connect_core.app.validation.email_validation.EmailValidation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/dept/admin")
public class DeptAdminController {
    private final DeptAdminService service;
    private final EmailValidation emailValidation;

    // required district admin login required for access this endpoint {id} -> login
    // with current admin id
    @GetMapping("/dist")
    public List<DeptAdminResponse> getDeptAdmins() {
        return service.getDeptAdminList();
    }

    // get login admin take their personal info
    @GetMapping
    public ResponseEntity<DeptAdminResponse> getDeptAdminProfile() {
        return ResponseEntity.ok(service.getDeptAdminProfile());
    }

    // only dist admin create new dept admin and assign department
    @PostMapping
    public ResponseEntity<?> newDeptAdmin(@Valid @RequestBody DeptAdminRequest request) {
        if (emailValidation.isEmailExist(request.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("Email", "Already Exist"));
        }
        return ResponseEntity.ok(service.insertDeptAdmin(request));
    }

}
