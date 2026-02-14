package com.civic_connect_core.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminRegReqDTO;
import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminRegResDTO;
import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminUpdateReqDTO;
import com.civic_connect_core.app.entities.DistrictAdmin;
import com.civic_connect_core.app.mapper.DistAdminMapper;
import com.civic_connect_core.app.repository.DistAdminRepo;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/dist/admin")
public class DistAdminController {
    private final DistAdminMapper distMapper;

    private final DistAdminRepo repository;

    private final PasswordEncoder passwordEncoder;


    // get all district admin list
    @GetMapping
    public List<DistAdminRegResDTO> getAllAdmins() {
        List<DistrictAdmin> admins = repository.findAll();
        return admins.stream().map(admin -> distMapper.tRegResDTO(admin)).toList();
    }

    // create new district admin
    @PostMapping
    public ResponseEntity<?> addDistAdmin(@Valid @RequestBody DistAdminRegReqDTO request) {
        if (repository.existsByAdminEmail(request.getAdminEmail())) {
            // {"Email":"Email Already Exist"}
            return ResponseEntity.badRequest().body(Map.of("Email", "Email Already Exist"));
        }
        var newAdmin = distMapper.toDistrictAdmin(request);
        newAdmin.setAdminPassword(passwordEncoder.encode(newAdmin.getAdminPassword()));
        repository.save(newAdmin);
        return ResponseEntity.ok(distMapper.tRegResDTO(newAdmin));
    }

    // update district admin account information
    @PutMapping("/{id}")
    public ResponseEntity<DistAdminRegResDTO> udpateDistAdmin(@RequestBody DistAdminUpdateReqDTO request,
            @PathVariable Long id) {
        if (request != null) {
            var admin = repository.findById(id).orElseThrow(() -> new RuntimeException());
            distMapper.updateDistAdminProfile(request, admin);
            repository.save(admin);
            var res = distMapper.tRegResDTO(admin);
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().build();
    }
}
