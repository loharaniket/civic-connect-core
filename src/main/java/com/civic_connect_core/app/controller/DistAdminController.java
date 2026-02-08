package com.civic_connect_core.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/dist/admin")
public class DistAdminController {
    private final DistAdminMapper distMapper;
    
    @Autowired
    private final DistAdminRepo repository;

    //get all district admin list
    @GetMapping
    public List<DistAdminRegResDTO> getAllAdmins() {
        List<DistrictAdmin> admins = repository.findAll();
        return admins.stream().map(admin -> distMapper.tRegResDTO(admin)).toList();
    }

    // create new district admin
    @PostMapping
    public ResponseEntity<DistAdminRegResDTO> addDistAdmin(@RequestBody DistAdminRegReqDTO request) {
        if (request != null) {
            var newAdmin = distMapper.toDistrictAdmin(request);
            repository.save(newAdmin);
            var res = distMapper.tRegResDTO(newAdmin);
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().build();
    }

    // update district admin account information
    @PutMapping("/{id}")
    public ResponseEntity<DistAdminRegResDTO> udpateDistAdmin(@RequestBody DistAdminUpdateReqDTO request, @PathVariable Long id) {
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
