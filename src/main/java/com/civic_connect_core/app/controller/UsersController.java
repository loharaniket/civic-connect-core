package com.civic_connect_core.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.user_dtos.UserReqDTO;
import com.civic_connect_core.app.dtos.user_dtos.UserResDTO;
import com.civic_connect_core.app.dtos.user_dtos.UserUpdateDTO;
import com.civic_connect_core.app.mapper.UsersMapper;
import com.civic_connect_core.app.repository.UsersRepo;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UsersController {
    private final UsersMapper userMapper;
    @Autowired
    private final UsersRepo userRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{user_id}")
    public ResponseEntity<UserResDTO> getUser(@PathVariable Long user_id) {
        if (user_id <= 0)
            return ResponseEntity.badRequest().build();
        var user = userRepo.findById(user_id).orElseThrow();
        return ResponseEntity.ok(userMapper.tUserResDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserResDTO> addUser(@Valid @RequestBody UserReqDTO request) {
        if (request == null)
            return ResponseEntity.badRequest().build();
        var user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return new ResponseEntity<>(userMapper.tUserResDTO(user), HttpStatus.CREATED);

    }

    @PutMapping("/{user_id}")
    public ResponseEntity<UserResDTO> updateUser(@RequestBody UserUpdateDTO request, @PathVariable Long user_id) {
        if (request == null)
            return ResponseEntity.badRequest().build();
        var user = userRepo.findById(user_id).orElseThrow();
        userMapper.updateUser(request, user);
        userRepo.save(user);
        return ResponseEntity.ok(userMapper.tUserResDTO(user));
    }
}
