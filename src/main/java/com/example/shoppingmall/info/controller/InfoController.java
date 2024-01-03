package com.example.shoppingmall.info.controller;

import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {
    /*회원 아이디/비밀번호 페이지*/
    @GetMapping(" ")
    public String goToIdPwPage(){
        return "info/find-id-pw";
    }

    /*아이디 찾기*/
    @PostMapping("/id")
    public String findId(){
        return "info/find-id";
    }

    /*아이디 찾기 성공 페이지*/
    @GetMapping("/id/find-success")
    public String findIdSuccess(){
        return "members/member-login";
    }

    /*비밀번호 찾기*/
    @PostMapping("/pw")
    public String findPw(){
        return"info/reset-pw";
    }

    /*비빌번호 재설정 페이지*/
    @GetMapping("/pw/update")
    public String goToPwUpdatePage(){
        return "info/reset-pw";
    }

    /*비밀번호 재설정 */
    @PostMapping("/pw/update")
    public String updatePw(){
        return "info/reset-pw";
    }

    /*비밀번호 재설정 완료*/
    @GetMapping("/pw/update-success")
    public String updatePwSuccess (){
        return "info/reset-pw-ok";
    }
}
