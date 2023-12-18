package com.example.shoppingmall.member.controller;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberAddDTO;
import com.example.shoppingmall.member.dto.MemberLoginDTO;
import com.example.shoppingmall.member.dto.MemberSearchDTO;
import com.example.shoppingmall.member.dto.MemberUpdateDTO;
import com.example.shoppingmall.member.service.MemberLoginService;
import com.example.shoppingmall.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberLoginService memberLoginService;

    /*유저 로그인 기능*/
    @GetMapping("/login")
    public String goToMemberLoginPage(){
        return "members/member-login";
    }

    @PostMapping("/login")
    public String memberLogin(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpServletRequest request){
        //실패 시
        //return members/member-login

        //성공 시
        Member loginMember = memberLoginService.login(memberLoginDTO);

        HttpSession session = request.getSession();
        session.setAttribute("loginMember",loginMember);
        session.setMaxInactiveInterval(1800);
        return "redirect:/";
    }

    /*유저 로그아웃 기능*/
    @PostMapping("/logout")
    public String memberLogout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

    /*유저 회원가입 기능*/
    @GetMapping("/add")
    public String goToAddMemberPage(){
        return "members/add-member";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute MemberAddDTO memberAddDTO){
        //실패 시

        //성공 시
        memberService.join(memberAddDTO);
        return "redirect:/members/add-success";
    }

    @GetMapping("/add-success")
    public String goToAddMemberSuccess(){
        return "members/add-member-success";
    }


    /*유저 관련 기능*/
    @GetMapping("/{memberNo}/show-info")
    public String showMemberInfo(@PathVariable(name="memberNo") Long memberNo){
        return "members/member-info";
    }

    @GetMapping("/{memberNo}/update")
    public String goToUpdateMemberPage(@PathVariable Long memberNo, @ModelAttribute MemberUpdateDTO memberUpdateDTO){
        return "members/update-member";
    }


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
