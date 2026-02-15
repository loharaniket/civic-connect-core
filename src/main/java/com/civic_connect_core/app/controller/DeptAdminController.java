package com.civic_connect_core.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminReqDTO;
import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminResDTO;
import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminUpdateReq;
import com.civic_connect_core.app.entities.DepartmentAdmin;
import com.civic_connect_core.app.mapper.DeptAdminMapper;
import com.civic_connect_core.app.repository.DeptAdminRepo;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/dept/admin")
public class DeptAdminController {
    private final DeptAdminMapper deptAdminMappper;
    @Autowired
    private final DeptAdminRepo deptRepo;

    // required district admin login required for access this endpoint {id} -> login
    // with current admin id
    @GetMapping("/dist/{id}")
    public ResponseEntity<List<DeptAdminResDTO>> getDeptAdmins(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity
                .ok(deptRepo.findByDistAdminId(id)
                        .stream()
                        .map(admin -> deptAdminMappper.tResDTO(admin))
                        .toList());
    }

    // get login admin take their personal info
    @GetMapping
    public ResponseEntity<DeptAdminResDTO> getDeptAdminProfile() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) auth.getPrincipal();
        DepartmentAdmin admin = deptRepo.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(deptAdminMappper.tResDTO(admin));
    }

    // only dist admin create new dept admin and assign department
    @PostMapping
    public ResponseEntity<DeptAdminResDTO> newDeptAdmin(@Valid @RequestBody DeptAdminReqDTO request) {
        if (request != null) {
            var admin = deptAdminMappper.tDepartmentAdmin(request);
            deptRepo.save(admin);
            return ResponseEntity.ok(deptAdminMappper.tResDTO(admin));
        }
        return ResponseEntity.badRequest().build();
    }

    // here dept admin update their own profile info
    @PutMapping("/{id}")
    public ResponseEntity<DeptAdminResDTO> udpateDeptAdmin(@RequestBody DeptAdminUpdateReq request,
            @PathVariable Long id) {
        if (request != null && id != null) {
            var existAdmin = deptRepo.findById(id).orElseThrow();
            deptAdminMappper.updateDeptAdmin(request, existAdmin);
            deptRepo.save(existAdmin);
            return ResponseEntity.ok(deptAdminMappper.tResDTO(existAdmin));
        }
        return ResponseEntity.badRequest().build();
    }

}
