package com.civic_connect_core.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civic_connect_core.app.entities.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long>{

}
