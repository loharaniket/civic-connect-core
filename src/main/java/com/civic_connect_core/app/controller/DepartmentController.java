package com.civic_connect_core.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.dept_dtos.DeptReqDTO;
import com.civic_connect_core.app.entities.Department;
import com.civic_connect_core.app.entities.DistrictAdmin;
import com.civic_connect_core.app.mapper.DeptMapper;
import com.civic_connect_core.app.repository.DeptRepo;
import com.civic_connect_core.app.repository.DistAdminRepo;
import com.civic_connect_core.app.utility.SecurityContextDetail;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/dept")
public class DepartmentController {
    // private final DeptMapper deptMapper;
    private final DeptRepo deptRepo;
    private final SecurityContextDetail securityContextDetail;
    private final DistAdminRepo distAdminRepo;

    // only dist admin get their own created department by using their actual id
    @GetMapping
    public ResponseEntity<List<Department>> getDeptList() {
        String email = securityContextDetail.getEmailFromContext();
        DistrictAdmin admin = distAdminRepo.findByAdminEmail(email).orElse(null);
        if (admin == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok(deptRepo.findByDistAdminId(admin.getId()));
    }

    // only dist admin create
    @PostMapping
    public ResponseEntity<Department> insertDept(@Valid @RequestBody DeptReqDTO request) {
        String email = securityContextDetail.getEmailFromContext();
        DistrictAdmin admin = distAdminRepo.findByAdminEmail(email).orElse(null);
        if (admin == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Department dept = Department.builder()
                .deptName(request.getDeptName())
                .distAdminId(admin.getId())
                .build();
        deptRepo.save(dept);
        return ResponseEntity.ok(dept);

    }

    // only dist admin can delete department provide actual of department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDept(@PathVariable Long id) {
        var dept = deptRepo.findById(id).orElseThrow(() -> new RuntimeException());
        deptRepo.delete(dept);
        return ResponseEntity.noContent().build();
    }

}
