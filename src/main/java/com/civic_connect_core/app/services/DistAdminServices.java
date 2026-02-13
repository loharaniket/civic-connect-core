package com.civic_connect_core.app.services;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.civic_connect_core.app.repository.DistAdminRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DistAdminServices implements UserDetailsService {
    private final DistAdminRepo distAdminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var distAdmin = distAdminRepo.findByAdminEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return new User(
                distAdmin.getAdminEmail(),
                distAdmin.getAdminPassword(),
                Collections.emptyList());
    }

}
