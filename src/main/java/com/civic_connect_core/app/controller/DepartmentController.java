package com.civic_connect_core.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.dept_dtos.DeptReqDTO;
import com.civic_connect_core.app.entities.Department;
import com.civic_connect_core.app.mapper.DeptMapper;
import com.civic_connect_core.app.repository.DeptRepo;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/dept/")
public class DepartmentController {
    private final DeptMapper deptMapper;
    @Autowired
    private final DeptRepo deptRepo;

    // only dist admin get their own created department by using their actual id
    @GetMapping("/{id}")
    public List<Department> getDepartments(@PathVariable Long id) {
        return deptRepo.findByDistAdminId(id);
    }

    // only dist admin create
    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody DeptReqDTO request) {
        var dept = deptMapper.toDepartment(request);
        deptRepo.save(dept);
        return new ResponseEntity<>(dept, HttpStatus.CREATED);
    }

    // only dist admin can delete department provide actual of department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDept(@PathVariable Long id) {
        var dept = deptRepo.findById(id).orElseThrow(() -> new RuntimeException());
        deptRepo.delete(dept);
        return ResponseEntity.noContent().build();
    }

}
