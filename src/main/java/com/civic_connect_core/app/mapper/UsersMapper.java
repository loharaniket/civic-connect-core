package com.civic_connect_core.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.civic_connect_core.app.dtos.user_dtos.UserReqDTO;
import com.civic_connect_core.app.dtos.user_dtos.UserResDTO;
import com.civic_connect_core.app.dtos.user_dtos.UserUpdateDTO;
import com.civic_connect_core.app.entities.Users;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    Users toUser(UserReqDTO request);

    UserResDTO tUserResDTO(Users user);

    void updateUser(UserUpdateDTO request, @MappingTarget Users user);
}
