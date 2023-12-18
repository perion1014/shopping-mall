package com.example.shoppingmall.admin.repository;

import com.example.shoppingmall.admin.domain.Admin;
import com.example.shoppingmall.admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisAdminRepository implements AdminRepository{

    private final AdminMapper adminMapper;
    @Override
    public Optional<Admin> findByAdminId(String adminId){
        return Optional.ofNullable((adminMapper.findByAdminId(adminId)));
    }
}
