package com.civic_connect_core.app.dtos.issues_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IssueReqDTO {
    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "description is required")
    @JsonProperty("desc")
    private String description;

    @NotBlank(message = "auther id is required")
    @JsonProperty("auther_id")
    private Long autherId;
}
