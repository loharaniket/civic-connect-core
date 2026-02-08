package com.civic_connect_core.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic_connect_core.app.entities.DepartmentAdmin;

public interface DeptAdminRepo extends JpaRepository<DepartmentAdmin, Long> {
    List<DepartmentAdmin> findByDistAdminId(Long distAdminId);
}
