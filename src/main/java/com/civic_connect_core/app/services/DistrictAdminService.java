package com.civic_connect_core.app.services;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminRequest;
import com.civic_connect_core.app.dtos.dist_admin_dtos.DistAdminResponse;
import com.civic_connect_core.app.dtos.dist_admin_dtos.DistrictPublicResponse;
import com.civic_connect_core.app.entities.DistrictAdmin;
import com.civic_connect_core.app.mapper.DistAdminMapper;
import com.civic_connect_core.app.repository.DistAdminRepo;
import com.civic_connect_core.app.utility.SecurityContextDetail;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DistrictAdminService {
    private final DistAdminRepo distAdminRepo;
    private final PasswordEncoder passwordEncoder;
    private final DistAdminMapper distAdminMapper;
    private final SecurityContextDetail contextDetail;

    public DistrictAdmin findByEmailDistAdmin(String email) {
        var admin = distAdminRepo.findByAdminEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return admin;
    }

    public boolean isDistAdminEmailExist(String email) {
        return distAdminRepo.findByAdminEmail(email).isPresent();
    }

    public boolean isDistrictExist(String district) {
        return distAdminRepo.findByDistName(district.toUpperCase()).isPresent();
    }

    public boolean isDistIdPresent(Long id) {
        return distAdminRepo.findById(id).isPresent();
    }

    public DistrictAdmin getContextDistAdmin() {
        String admin = contextDetail.getEmailFromContext();
        return distAdminRepo.findByAdminEmail(admin).orElseThrow(()-> new UsernameNotFoundException(admin));
    }

    public DistAdminResponse insertDistAdmin(DistAdminRequest request) {
        var admin = distAdminMapper.toDistrictAdmin(request);
        admin.setAdminPassword(passwordEncoder.encode(admin.getAdminPassword()));
        admin.setDistName(admin.getDistName().toUpperCase());
        distAdminRepo.save(admin);
        return distAdminMapper.tRegResDTO(admin);
    }

    public DistAdminResponse getDistAdminProfile() {
        String email = contextDetail.getEmailFromContext();
        var admin = distAdminRepo.findByAdminEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return distAdminMapper.tRegResDTO(admin);
    }

    public List<DistrictPublicResponse> getAllDistrictList() {
        return distAdminRepo.findAll().stream().map(dist -> distAdminMapper.tDistrictPublicResponse(dist)).toList();
    }
}
