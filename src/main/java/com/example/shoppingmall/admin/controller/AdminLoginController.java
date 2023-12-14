package com.example.shoppingmall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminLoginController {

    @GetMapping("/admins/login")
    public String goToAdminLoginPage(){
        return "/admins/admin_login";
    }
    @PostMapping("/admins/login")
    public String adminLogin(){
        return "/admins/admin_mainTest";
    }
    @PostMapping("/admins/logout")
    public String adminLogout(){
        return "/admins/admin_login";
    }

}
