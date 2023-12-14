package com.example.shoppingmall.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Integer adminNo;
    private String adminId;
    private String adminPw;
}
