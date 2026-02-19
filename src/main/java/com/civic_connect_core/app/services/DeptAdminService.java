package com.civic_connect_core.app.services;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminRequest;
import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminResponse;
import com.civic_connect_core.app.entities.DepartmentAdmin;
import com.civic_connect_core.app.mapper.DeptAdminMapper;
import com.civic_connect_core.app.repository.DeptAdminRepo;
import com.civic_connect_core.app.utility.SecurityContextDetail;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeptAdminService {
    private final DeptAdminRepo repository;
    private final DeptAdminMapper mapper;
    private final SecurityContextDetail context;
    private final DistrictAdminService districtAdminService;
    private final PasswordEncoder passwordEncoder;

    public boolean isDeptAdminEmailExist(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public DepartmentAdmin getDepartmentAdminDetail() {
        String email = context.getEmailFromContext();
        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public DeptAdminResponse insertDeptAdmin(DeptAdminRequest request) {
        var distAdmin = districtAdminService.getContextDistAdmin();
        var deptAdmin = mapper.tDepartmentAdmin(request);
        deptAdmin.setPassword(passwordEncoder.encode(deptAdmin.getPassword()));
        deptAdmin.setDistAdminId(distAdmin.getId());
        repository.save(deptAdmin);
        return mapper.tResDTO(deptAdmin);
    }

    public DeptAdminResponse getDeptAdminProfile() {
        return mapper.tResDTO(getDepartmentAdminDetail());
    }

    public List<DeptAdminResponse> getDeptAdminList() {
        var distAdmin = districtAdminService.getContextDistAdmin();
        return repository.findByDistAdminId(distAdmin.getId()).stream().map(admin -> mapper.tResDTO(admin)).toList();
    }

}
