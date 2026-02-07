package com.civic_connect_core.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civic_connect_core.app.entities.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

}
