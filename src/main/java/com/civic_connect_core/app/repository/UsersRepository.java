package com.civic_connect_core.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civic_connect_core.app.entity.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByUserEmail(String userEmail);

    boolean existsByUserId(Long userId);

    Optional<Users> findByUserId(Long userId);

    Optional<Users> findByUserEmail(String userEmail);
}
