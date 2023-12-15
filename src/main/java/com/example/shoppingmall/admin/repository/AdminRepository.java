package com.example.shoppingmall.admin.repository;

import com.example.shoppingmall.admin.domain.Admin;

import java.util.Optional;

public interface AdminRepository {
    void adminLogin(Admin admin);

}
