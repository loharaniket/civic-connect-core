package com.civic_connect_core.app.dtos.dept_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeptReqDTO {
    @JsonProperty("dept_name")
    private String deptName;
    @JsonProperty("dist_admin_id")
    private Long distAdminId;
}
