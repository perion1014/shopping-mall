package com.example.shoppingmall.admin.service;

import com.example.shoppingmall.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
//    로그인시 DB와 아이디 일치여부 확인 미구현

}
