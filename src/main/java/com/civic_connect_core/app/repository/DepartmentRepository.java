package com.civic_connect_core.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civic_connect_core.app.entity.Departments;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Departments, Long> {
    boolean existsByDeptName(String deptName);

    Departments findByDeptId(Long deptId);
}
