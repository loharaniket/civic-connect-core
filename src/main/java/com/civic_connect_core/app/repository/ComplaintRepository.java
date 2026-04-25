package com.civic_connect_core.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civic_connect_core.app.entity.Complaint;
import com.civic_connect_core.app.entity.Department;
import com.civic_connect_core.app.entity.Users;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByDeptId(Department dept);

    List<Complaint> findByUserId(Users user);

    Optional<Complaint> findById(Long id);
}
