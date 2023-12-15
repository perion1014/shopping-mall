package com.example.shoppingmall.admin.service;

import com.example.shoppingmall.admin.domain.Admin;
import com.example.shoppingmall.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private  final AdminRepository adminRepository;
    @Transactional//
    public void join(Admin admin){
        adminRepository.adminLogin(admin);


    }
}

