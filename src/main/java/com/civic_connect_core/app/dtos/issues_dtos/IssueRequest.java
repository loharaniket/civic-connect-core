package com.civic_connect_core.app.dtos.issues_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IssueRequest {
    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "description is required")
    @JsonProperty("desc")
    private String description;

    @JsonProperty("department")
    private Long dept_id;
}
