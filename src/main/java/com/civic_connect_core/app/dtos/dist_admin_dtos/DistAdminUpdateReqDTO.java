package com.civic_connect_core.app.dtos.dist_admin_dtos;

import lombok.Data;

@Data
public class DistAdminUpdateReqDTO {
    private String adminName;
    private String adminSurname;
    private String adminEmail;
}
