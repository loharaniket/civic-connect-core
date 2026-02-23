package com.civic_connect_core.app.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.civic_connect_core.app.dtos.user_dtos.UserRequest;
import com.civic_connect_core.app.dtos.user_dtos.UserResponse;
import com.civic_connect_core.app.entities.Users;
import com.civic_connect_core.app.mapper.UsersMapper;
import com.civic_connect_core.app.repository.UsersRepo;
import com.civic_connect_core.app.utility.SecurityContextDetail;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepo repository;
    private final SecurityContextDetail context;
    private final UsersMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse insertUser(UserRequest request) {
        var user = mapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return mapper.tUserResDTO(user);
    }

    public Users getUserDetail() {
        String email = context.getEmailFromContext();
        var user = repository.findByUserEmail(email).orElseThrow();
        return user;

    }

    public UserResponse getUserDetailById(Long id) {
        var user = repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found at getUserDetailsById UsersService"));
        return mapper.tUserResDTO(user);
    }

    public UserResponse getUserProfile() {
        var user = getUserDetail();
        return mapper.tUserResDTO(user);
    }

}
