package com.example.shoppingmall.admin.dto;

import com.example.shoppingmall.admin.domain.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginDTO {
    private String adminId;
    private String adminPw;

    public static Admin AdminLoginDTOtoAdmin(AdminLoginDTO adminLoginDTO) {
        Admin admin = new Admin();
        admin.setAdminId(adminLoginDTO.getAdminId());
        admin.setAdminPw(adminLoginDTO.getAdminPw());
        return admin;
    }
}
