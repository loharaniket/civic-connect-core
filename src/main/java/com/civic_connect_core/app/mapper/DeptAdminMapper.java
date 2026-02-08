package com.civic_connect_core.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminReqDTO;
import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminResDTO;
import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminUpdateReq;
import com.civic_connect_core.app.entities.DepartmentAdmin;

@Mapper(componentModel = "spring")
public interface DeptAdminMapper {
    DeptAdminResDTO tResDTO(DepartmentAdmin request);

    DepartmentAdmin tDepartmentAdmin(DeptAdminReqDTO request);

    void updateDeptAdmin(DeptAdminUpdateReq request, @MappingTarget DepartmentAdmin admin);
}
