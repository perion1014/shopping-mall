package com.example.shoppingmall.admin.dto;

import com.example.shoppingmall.admin.domain.Admin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginDTO {
    private String adminId;
    private String adminPw;

    public static Admin AdminDTOtoAdmin(AdminLoginDTO adminDto) {
        Admin admin = new Admin();
        admin.setAdmin_id(adminDto.adminId);
        admin.setAdmin_pw(adminDto.adminPw);
        return admin;
    }
}
