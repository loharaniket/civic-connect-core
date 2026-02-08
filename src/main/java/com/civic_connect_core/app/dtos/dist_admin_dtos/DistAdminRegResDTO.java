package com.civic_connect_core.app.dtos.dist_admin_dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Data
public class DistAdminRegResDTO {
    private Long id;
    private String distName;
    private String distState;
    private String adminName;
    private String adminSurname;
    private String adminEmail;
    private String adminPassword;
}
