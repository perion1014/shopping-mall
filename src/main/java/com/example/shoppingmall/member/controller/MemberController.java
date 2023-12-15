package com.example.shoppingmall.member.controller;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberAddDTO;
import com.example.shoppingmall.member.dto.MemberLoginDTO;
import com.example.shoppingmall.member.dto.MemberSearchDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {

    /*유저 로그인 기능*/

    @GetMapping("/login")
    public String goToMemberLoginPage(){
        return "members/member-login";
    }

    @PostMapping("/login")
    public String memberLogin(){
        //Member member = MemberLoginDTO.MemberLoginDTOToMember(memberLoginDTO);
        //service
        //멤버서비스(멤버).회원가입
        //성공 -> return "home"
        //실패 -> return ="원래페이지"

        //실패시
        //return members/member-login

        //성공 시
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String memberLogout(){
        return "redirect:/";
    }

    /*유저 회원가입 기능*/

    @GetMapping("/add")
    public String goToAddMemberPage(){
        return "members/add-member";
    }

    @PostMapping("/add")
    public String addMember(){
        //(@ModelAttribute MemberAddDTO memberAddDTO
        //Member member = MemberAddDTO.MemberAddDTOToMember(memberAddDTO);
        //서비스
        return "redirect:/members/add-success";
    }

    @GetMapping("/add-success")
    public String goToAddMemberSuccess(){
        return "members/add-member-success";
    }

    /*유저 기능*/



    /*관리자 기능*/

    //회원 목록 조회(뷰 완성 시, String타입으로 변경)
    @GetMapping("/admin")
    public void showMemberList(){
        //return "admins/admin_member";
    }

    @PostMapping("/admin")  //(완성 시, String타입으로 변경)
    public void searchMembers(@ModelAttribute MemberSearchDTO memberSearchDto){

    }

    @PostMapping("/delete")
    public void deleteMembers(){

    }


}
