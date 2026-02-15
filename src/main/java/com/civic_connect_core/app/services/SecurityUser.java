package com.civic_connect_core.app.services;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.civic_connect_core.app.entities.DepartmentAdmin;
import com.civic_connect_core.app.entities.DistrictAdmin;
import com.civic_connect_core.app.entities.Users;


public class SecurityUser implements UserDetails {
    private final String email;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public SecurityUser(DistrictAdmin distAdmin) {
        this.email = distAdmin.getAdminEmail();
        this.password = distAdmin.getAdminPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_"+distAdmin.getRole()));
    }

    public SecurityUser(Users user) {
        this.email = user.getUserEmail();
        this.password = user.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }
    
    public SecurityUser(DepartmentAdmin deptAdmin) {
        this.email = deptAdmin.getEmail();
        this.password = deptAdmin.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_"+deptAdmin.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
