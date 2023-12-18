package com.example.shoppingmall.admin.service;

import com.example.shoppingmall.admin.domain.Admin;
import com.example.shoppingmall.admin.dto.AdminLoginDTO;
import com.example.shoppingmall.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminLoginService {

    private final AdminRepository adminRepository;

    @Transactional
    public Admin loginAdmin(AdminLoginDTO adminLoginDTO){
        Admin admin = AdminLoginDTO.AdminLoginDTOToAdmin(adminLoginDTO);
        return adminRepository.findByAdminId(admin.getAdminId())
                .filter(a->a.getAdminPw().equals(admin.getAdminPw()))
                .orElse(null);
    }
}
