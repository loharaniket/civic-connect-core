package com.civic_connect_core.app.dtos.issues_dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "title", "description", "status", "createdAt" })
public class IssueResDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
}
