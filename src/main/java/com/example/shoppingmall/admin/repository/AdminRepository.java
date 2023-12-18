package com.example.shoppingmall.admin.repository;

import com.example.shoppingmall.admin.domain.Admin;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface AdminRepository {
    void adminLogin(Admin admin);

}
