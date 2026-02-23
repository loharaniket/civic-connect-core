package com.civic_connect_core.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.dept_dtos.DepartmentRequest;
import com.civic_connect_core.app.entities.Department;
import com.civic_connect_core.app.services.DepartmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/dept")
public class DepartmentController {
    private final DepartmentService service;

    // any one can access this endpoint
    @GetMapping("/public")
    public List<Department> getAllDepartments() {
        return service.getAllDepartmentList();
    }

    @GetMapping("/public/dist/{id}")
    public List<Department> getDeptByDistAdmin(@PathVariable Long id) {
        return service.getDeptByDistAdmin(id);
    }

    @GetMapping("/public/dept-name/{id}")
    public ResponseEntity<?> getDeptNameById(@PathVariable Long id) {
        return ResponseEntity.ok().body(Map.of("department", service.getDeptName(id)));
    }

    // only dist admin get their own created department by using their actual id
    @GetMapping
    public List<Department> getDeptList() {
        return service.getDepartmentList();
    }

    // only dist admin create
    @PostMapping
    public ResponseEntity<Department> insertDept(@Valid @RequestBody DepartmentRequest request) {
        return ResponseEntity.ok(service.insertDepartment(request));
    }

}
