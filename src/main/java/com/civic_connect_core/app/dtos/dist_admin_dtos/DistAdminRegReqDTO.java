package com.civic_connect_core.app.dtos.dist_admin_dtos;

import lombok.Data;

@Data
public class DistAdminRegReqDTO {
    private String distName;
    private String distState;
    private String adminName;
    private String adminSurname;
    private String adminEmail;
    private String adminPassword;
}