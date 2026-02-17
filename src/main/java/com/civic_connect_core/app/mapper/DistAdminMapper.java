package com.civic_connect_core.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminRequest;
import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminResponse;
import com.civic_connect_core.app.entities.DistrictAdmin;

@Mapper(componentModel = "spring")
public interface DistAdminMapper {
    DistrictAdmin toDistrictAdmin(DistAdminRequest request);

    DistAdminResponse tRegResDTO(DistrictAdmin admin);

    List<DistAdminResponse> toListAdmin(List<DistrictAdmin> admins);
}
