package com.civic_connect_core.app.dtos.dept_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeptReqDTO {
    @NotEmpty(message = "department name required")
    @JsonProperty("dept_name")
    private String deptName;
    
    @NotEmpty(message = "district admin id required")
    @JsonProperty("dist_admin_id")
    private Long distAdminId;
}
