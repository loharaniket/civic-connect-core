package com.civic_connect_core.app.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long userId;
    private String userEmail;
    @JsonIgnore
    private String userPassword;
    private String userRole;
    private LocalDateTime createdAt;
}
