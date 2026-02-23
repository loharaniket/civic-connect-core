package com.civic_connect_core.app.dtos.issues_dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateIssueStatus {
    @NotBlank(message = "status is required")
    private String status;

    @NotNull(message = "issue id is required")
    private Long issueId;
}
