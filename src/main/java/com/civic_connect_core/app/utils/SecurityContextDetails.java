package com.civic_connect_core.app.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextDetails {

    public static Long getUserContextId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (long) auth.getPrincipal();
    }

}
