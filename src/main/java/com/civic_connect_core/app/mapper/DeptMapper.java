package com.civic_connect_core.app.mapper;

import org.mapstruct.Mapper;

import com.civic_connect_core.app.dtos.dept_dtos.DeptReqDTO;
import com.civic_connect_core.app.entities.Department;

@Mapper(componentModel = "spring")
public interface DeptMapper {
    Department toDepartment(DeptReqDTO request);
    
}
