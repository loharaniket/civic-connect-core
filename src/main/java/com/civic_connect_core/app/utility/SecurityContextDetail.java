package com.civic_connect_core.app.utility;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextDetail {

    public String getEmailFromContext() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) auth.getPrincipal();
        System.out.println("Done getEmailFromContext():- "+ email);
        return email;
    }

}
