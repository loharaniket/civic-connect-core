package com.civic_connect_core.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civic_connect_core.app.entity.UserProfile;
import com.civic_connect_core.app.entity.Users;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUsers(Users users);
    // List<UserProfile> 
}
