package com.civic_connect_core.app.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @JsonIgnore
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Column(name = "USER_ROLE")
    private String userRole;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
}
