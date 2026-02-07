package com.civic_connect_core.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "dist_admin")
public class DistrictAdmin {
    @Id
    @Column(name = "dist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String distName;
    private String distState;
    private String adminName;
    private String adminSurname;
    private String adminEmail;
    private String adminPassword;
}
