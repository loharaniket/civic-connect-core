package com.civic_connect_core.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civic_connect_core.app.entity.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByUserEmail(String userEmail);
}
