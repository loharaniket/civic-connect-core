package com.civic_connect_core.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civic_connect_core.app.entities.Department;
import java.util.List;


@Repository
public interface DeptRepo extends JpaRepository<Department, Long> {
    List<Department> findByDistAdminId(Long distAdminId);
}
