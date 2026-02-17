package com.civic_connect_core.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.civic_connect_core.app.dtos.user_dtos.UserRequest;
import com.civic_connect_core.app.dtos.user_dtos.UserResponse;
import com.civic_connect_core.app.dtos.user_dtos.UserUpdateRequest;
import com.civic_connect_core.app.entities.Users;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    Users toUser(UserRequest request);

    UserResponse tUserResDTO(Users user);

    void updateUser(UserUpdateRequest request, @MappingTarget Users user);
}
