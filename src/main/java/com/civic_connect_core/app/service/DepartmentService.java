package com.civic_connect_core.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.civic_connect_core.app.dtos.request.SaveDepartmentRequest;
import com.civic_connect_core.app.entity.Department;
import com.civic_connect_core.app.enums.UserRoles;
import com.civic_connect_core.app.exceptions.DepartmentAlreadyExist;
import com.civic_connect_core.app.exceptions.DepartmentNotFoundException;
import com.civic_connect_core.app.exceptions.UserNotFoundException;
import com.civic_connect_core.app.repository.DepartmentRepository;
import com.civic_connect_core.app.repository.UsersRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UsersRepository usersRepository;

    private List<Department> departmentList() {
        return departmentRepository.findAll();
    }

    public List<Department> getAllDepartmentList() {
        return departmentList();
    }

    public List<Department> notAssignDepartment() {
        List<Department> deptList = departmentList();
        for (Department dept : deptList) {
            if (dept.getUserId() != null) {
                deptList.remove(dept);
            }
        }
        return deptList;
    }

    public Department saveDepartment(SaveDepartmentRequest request) {
        if (departmentRepository.existsByDeptName(request.getDeptName().toUpperCase())) {
            throw new DepartmentAlreadyExist("Department Name Already Exist");
        }
        var dept = Department.builder()
                .deptName(request.getDeptName().toUpperCase())
                .userId(null).build();
        departmentRepository.save(dept);
        return dept;
    }

    @Transactional
    public void assignAdmin(Long deptId, Long userId) {
        var dept = departmentRepository.findByDeptId(deptId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department Not Found"));
        var user = usersRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        dept.setUserId(user);
        departmentRepository.save(dept);
        user.setUserRole(UserRoles.DEPT_ADMIN.name());
        usersRepository.save(user);
    }

}
