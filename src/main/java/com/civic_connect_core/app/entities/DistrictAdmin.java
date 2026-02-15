package com.civic_connect_core.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "dist_admin")
public class DistrictAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String distName;
    private String distState;
    private String adminName;
    private String adminSurname;
    private String adminEmail;
    private String adminPassword;
    private String role = "SUPER_ADMIN";
}
