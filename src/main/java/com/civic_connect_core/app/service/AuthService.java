package com.civic_connect_core.app.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.civic_connect_core.app.dtos.AuthDTO.AuthRequest;
import com.civic_connect_core.app.entity.Users;
import com.civic_connect_core.app.exceptions.UserAlreadyExistException;
import com.civic_connect_core.app.repository.UsersRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final UsersRepository usersRepository;

    public Users register(AuthRequest request) {
        if (usersRepository.existsByUserEmail(request.getUserEmail())) {
            throw new UserAlreadyExistException("User Already Exist");
        }
        var user = Users.builder()
                .userEmail(request.getUserEmail())
                .userPassword(request.getUserPassword())
                .userRole("CITIZEN")
                .createdAt(LocalDateTime.now())
                .build();
        usersRepository.save(user);
        return user;
    }

}
