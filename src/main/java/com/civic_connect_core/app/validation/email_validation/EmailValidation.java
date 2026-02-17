package com.civic_connect_core.app.validation.email_validation;

import org.springframework.stereotype.Component;

import com.civic_connect_core.app.repository.DeptAdminRepo;
import com.civic_connect_core.app.repository.DistAdminRepo;
import com.civic_connect_core.app.repository.UsersRepo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailValidation {
    private final DistAdminRepo distAdminRepo;
    private final DeptAdminRepo deptAdminRepo;
    private final UsersRepo usersRepo;

    public boolean isEmailExist(String email) {
        boolean distAdmin = distAdminRepo.findByAdminEmail(email).isPresent();
        boolean deptAdmin = deptAdminRepo.findByEmail(email).isPresent();
        boolean user = usersRepo.findByUserEmail(email).isPresent();
        return distAdmin || deptAdmin || user;
    }

}
