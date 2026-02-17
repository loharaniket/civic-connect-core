package com.civic_connect_core.app.mapper;

import org.mapstruct.Mapper;

import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminRequest;
import com.civic_connect_core.app.dtos.dept_admin_dtos.DeptAdminResponse;
import com.civic_connect_core.app.entities.DepartmentAdmin;

@Mapper(componentModel = "spring")
public interface DeptAdminMapper {
    DeptAdminResponse tResDTO(DepartmentAdmin request);

    DepartmentAdmin tDepartmentAdmin(DeptAdminRequest request);
}
