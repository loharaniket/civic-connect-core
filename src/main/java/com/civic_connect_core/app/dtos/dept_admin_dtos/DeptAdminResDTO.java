package com.civic_connect_core.app.dtos.dept_admin_dtos;

import lombok.Data;

@Data
public class DeptAdminResDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long deptId;
}
