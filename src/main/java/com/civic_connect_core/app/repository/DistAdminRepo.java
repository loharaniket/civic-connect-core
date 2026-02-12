package com.civic_connect_core.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civic_connect_core.app.entities.DistrictAdmin;


@Repository
public interface DistAdminRepo extends JpaRepository<DistrictAdmin, Long> {
    Optional<DistrictAdmin> findByAdminEmail(String adminEmail);
    Optional<DistrictAdmin> findByDistName(String distName);
}
