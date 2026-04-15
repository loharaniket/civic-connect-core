package com.civic_connect_core.app.dtos.AuthDTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String userEmail;
    private String userPassword;
}
