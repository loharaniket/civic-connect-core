package com.civic_connect_core.app.dtos.issues_dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({ "id", "title", "description", "status", "latitude", "longitude", "image", "department",
        "createdAt" })
public class IssueResponse {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Double latitude;
    private Double longitude;
    private String imageLocation;
    private String department;
    private LocalDateTime createdAt;
}
