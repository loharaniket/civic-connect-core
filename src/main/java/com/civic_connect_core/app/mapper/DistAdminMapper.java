package com.civic_connect_core.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminRegReqDTO;
import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminRegResDTO;
import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminUpdateReqDTO;
import com.civic_connect_core.app.entities.DistrictAdmin;

@Mapper(componentModel = "spring")
public interface DistAdminMapper {
    DistrictAdmin toDistrictAdmin(DistAdminRegReqDTO request);

    DistAdminRegResDTO tRegResDTO(DistrictAdmin request);

    List<DistAdminRegResDTO> toListAdmin(List<DistrictAdmin> admins);

    void updateDistAdminProfile(DistAdminUpdateReqDTO request, @MappingTarget DistrictAdmin admin);
}
