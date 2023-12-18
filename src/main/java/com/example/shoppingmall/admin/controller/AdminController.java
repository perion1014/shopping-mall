package com.example.shoppingmall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins")/*절대경로*/
public class AdminController {

    @GetMapping("/login")
    public String goToAdminLoginPage(){
        return "admins/admin-login";/*상대경로*/

    }

    @PostMapping("/login")
    public String adminLogin(String adminId, int adminPw){
        return "admins/admin-homeTest";

    }
    @PostMapping("/logout")
    public String adminLogout(){
        return "admins/admin-login";
    }

}
