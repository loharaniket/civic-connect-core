package com.civic_connect_core.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DistrictEntity {
    @Id
    @Column(name = "dist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String distName;
    private String distState;
}
