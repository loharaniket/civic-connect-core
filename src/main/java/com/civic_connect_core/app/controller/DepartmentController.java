package com.civic_connect_core.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.request.SaveDepartmentRequest;
import com.civic_connect_core.app.dtos.response.ApiResponse;
import com.civic_connect_core.app.entity.Department;
import com.civic_connect_core.app.service.DepartmentService;

import lombok.AllArgsConstructor;

@RequestMapping("api/v1/department")
@RestController
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    // Department:
    // 1) Get All Departments List with Assign Admin
    @GetMapping
    public ResponseEntity<ApiResponse<List<Department>>> getAllDepartments() {
        var getDept = departmentService.getAllDepartmentList();
        var response = new ApiResponse<>(true, "Department Successfully Loaded", getDept, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 2) Get Department that not assign any admin
    @GetMapping("/not_assign")
    public ResponseEntity<ApiResponse<List<Department>>> notAssignDepartments() {
        var getDept = departmentService.notAssignDepartment();
        var response = new ApiResponse<>(true, "Department Successfully Loaded", getDept, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 3) departments -> POST -> (Admin) add new department
    @PostMapping
    public ResponseEntity<ApiResponse<Department>> saveDeparment(@RequestBody SaveDepartmentRequest request) {
        var dept = departmentService.saveDepartment(request);
        var response = new ApiResponse<>(true, "Department Created Sucessfully", dept, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 4) departments/{id}/admin/{userId} -> POST -> (Admin) assign department to
    // new admin
    @PostMapping("/{id}/admin/{userId}")
    public ResponseEntity<ApiResponse<Object>> assignAdmin(@PathVariable Long id, @PathVariable Long userId) {
        departmentService.assignAdmin(id, userId);
        var response = new ApiResponse<>(true, "Update Successfully", null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // 5) departments/{id}/admin -> GET -> (Admin) view full assign admin

}
