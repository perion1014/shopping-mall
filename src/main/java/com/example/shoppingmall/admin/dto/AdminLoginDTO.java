package com.example.shoppingmall.admin.dto;

import com.example.shoppingmall.admin.domain.Admin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginDTO {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String adminId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String adminPw;

    public static Admin AdminLoginDTOToAdmin(AdminLoginDTO adminLoginDTO) {
        Admin admin = new Admin();
        admin.setAdminId(adminLoginDTO.getAdminId());
        admin.setAdminPw(adminLoginDTO.getAdminPw());

        return admin;
    }
}
