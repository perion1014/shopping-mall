package com.example.shoppingmall.admin.service;

import com.example.shoppingmall.admin.domain.Admin;
import com.example.shoppingmall.admin.dto.AdminLoginDTO;
import com.example.shoppingmall.admin.mapper.AdminMapper;
import com.example.shoppingmall.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;

//    public String adminAutority(String adminId){
//        String resultId = adminMapper.adminLogin(adminId);
//    return resultId;
//    }
}
