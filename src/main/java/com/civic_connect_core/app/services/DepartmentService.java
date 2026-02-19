package com.civic_connect_core.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.civic_connect_core.app.dtos.dept_dtos.DepartmentRequest;
import com.civic_connect_core.app.entities.Department;
import com.civic_connect_core.app.entities.DistrictAdmin;
import com.civic_connect_core.app.mapper.DeptMapper;
import com.civic_connect_core.app.repository.DeptRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DeptRepo repository;
    private final DeptMapper mapper;
    private final DistrictAdminService districtAdminService;

    public List<Department> getAllDepartmentList() {
        return repository.findAll();
    }

    public List<Department> getDepartmentList() {
        DistrictAdmin admin = districtAdminService.getContextDistAdmin();
        return repository.findByDistAdminId(admin.getId());
    }

    public boolean isDepartmentIdPresent(Long id) {
        return repository.findById(id).isPresent();
    }

    public Department insertDepartment(DepartmentRequest request) {
        var distAdmin = districtAdminService.getContextDistAdmin();
        var dept = mapper.toDepartment(request);
        dept.setDistAdminId(distAdmin.getId());
        repository.save(dept);
        return dept;
    }
}
