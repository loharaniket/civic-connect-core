package com.civic_connect_core.app.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.civic_connect_core.app.entities.DepartmentAdmin;
import com.civic_connect_core.app.entities.DistrictAdmin;
import com.civic_connect_core.app.entities.Users;
import com.civic_connect_core.app.repository.DeptAdminRepo;
import com.civic_connect_core.app.repository.DistAdminRepo;
import com.civic_connect_core.app.repository.UsersRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UsersRepo usersRepo;
    private final DistAdminRepo distAdminRepo;
    private final DeptAdminRepo deptAdminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<DistrictAdmin> admin = distAdminRepo.findByAdminEmail(email);
        if (admin.isPresent()) {
            return new SecurityUser(admin.get());

        }

        Optional<DepartmentAdmin> deptAdmin = deptAdminRepo.findByEmail(email);
        if (deptAdmin.isPresent()) {
            return new SecurityUser(deptAdmin.get());
        }

        Optional<Users> user = usersRepo.findByUserEmail(email);
        if (user.isPresent()) {
            return new SecurityUser(user.get());
        }

        throw new UsernameNotFoundException("user not found");
    }

}
