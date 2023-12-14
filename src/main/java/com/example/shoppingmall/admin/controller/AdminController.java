package com.example.shoppingmall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins")
public class AdminController {

    @GetMapping("/login")
    public String goToAdminLoginPage(){
        return "/admins/admin_login";
    }
    @PostMapping("/login")
    public String adminLogin(){
        return "/admins/admin-homeTest";
    }
    @PostMapping("/logout")
    public String adminLogout(){
        return "/admins/admin_login";
    }

}
