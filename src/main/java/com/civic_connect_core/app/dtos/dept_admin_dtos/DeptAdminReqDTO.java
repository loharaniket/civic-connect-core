package com.civic_connect_core.app.dtos.dept_admin_dtos;

import lombok.Data;

@Data
public class DeptAdminReqDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long distAdminId;
    private Long deptId;
}
