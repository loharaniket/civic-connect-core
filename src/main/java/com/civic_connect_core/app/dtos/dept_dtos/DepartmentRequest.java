package com.civic_connect_core.app.dtos.dept_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentRequest {
    @NotBlank(message = "department is required")
    @JsonProperty("department")
    private String deptName;
}
