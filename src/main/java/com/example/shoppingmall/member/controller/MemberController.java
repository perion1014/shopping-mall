package com.example.shoppingmall.member.controller;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.*;
import com.example.shoppingmall.member.form.MemberPageForm;
import com.example.shoppingmall.member.form.MemberSearchForm;
import com.example.shoppingmall.member.service.MemberInfoService;
import com.example.shoppingmall.member.service.MemberLoginService;
import com.example.shoppingmall.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberLoginService memberLoginService;
    private final MemberInfoService memberInfoService;

    /*유저 로그인*/
    @GetMapping("/login")
    public String goToMemberLoginPage(){
        return "members/member-login";
    }

    @PostMapping("/login")
    public String memberLogin(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpServletRequest request){
        //실패 시

        //성공 시
        Member loginMember = memberLoginService.login(memberLoginDTO);

        HttpSession session = request.getSession();
        session.setAttribute("loginMember",loginMember);
        session.setMaxInactiveInterval(1800);
        return "redirect:/";
    }

    /*유저 로그 아웃*/
    @PostMapping("/logout")
    public String memberLogout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

    /*유저 회원 가입 기능*/
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


    /*유저 회원 상세 정보 조회 페이지*/
    @GetMapping("/{memberNo}/show-info")
    public String showMemberInfo(@PathVariable(name="memberNo") Long memberNo){
        return "members/member-info";
    }

    /*유저 회원 정보 조회*/
    @GetMapping("/{memberNo}/update")
    public String goToUpdateMemberPage(@PathVariable(name="memberNo") Long memberNo, Model model){
        model.addAttribute("memberUpdateDTO",memberService.getMemberInfo(memberNo));
        return "members/update-member";
    }

    /*유저 회원 정보 수정*/
    @PostMapping("/{memberNo}/update")
    public String updateMemberInfo(@ModelAttribute MemberUpdateDTO memberUpdateDTO, RedirectAttributes redirectAttributes){
        memberService.update(memberUpdateDTO);
        redirectAttributes.addFlashAttribute("memberUpdateSuccess", "회원 정보가 업데이트되었습니다.");
        return "redirect:/members/{memberNo}/update";
    }

    /*유저 회원 정보 탈퇴*/
    @GetMapping("/{memberNo}/delete")
    public String goToDeleteMemberPage(@PathVariable(name="memberNo") Long memberNo, Model model){
        model.addAttribute("memberDeleteDTO",new MemberDeleteDTO());
        return "members/delete-member";
    }

    @PostMapping("/{memberNo}/delete")
    public String deleteMember(@PathVariable(name="memberNo") Long memberNo,@ModelAttribute MemberDeleteDTO memberDeleteDTO, RedirectAttributes redirectAttributes, HttpServletRequest request){
        memberService.withdraw(memberNo, memberDeleteDTO);
        HttpSession session = request.getSession(false);
        session.invalidate();
        redirectAttributes.addFlashAttribute("memberUpdateSuccess", "회원 탈퇴가 완료되었습니다.");
        return "redirect:/members/delete-success";
    }

    @GetMapping("/delete-success")
    public String goToDeleteMemberSuccess(){
        return "members/delete-member-success";
    }


    /* 관리자 회원 목록 조회(페이징 처리 완료)*/
    @GetMapping("/admin")
    public String showMemberList(Model model,
                         @RequestParam(value="page", required=false, defaultValue="1") int page) {

        model.addAttribute("pageSettings", memberService.setMemberListPage(page));
        model.addAttribute("memberListByPaging",memberService.getMemberListPage(page));

        return "admins/admin-member";
    }

    /*관리자 회원 추방*/
    @PostMapping("/delete")
    public String deleteMembers(@RequestParam("memberNo")Long memberNo, RedirectAttributes redirectAttributes){
        memberService.drop(memberNo);
        redirectAttributes.addFlashAttribute("memberUpdateSuccess", "회원 추방이 완료되었습니다.");
        return "redirect:/members/admin";
    }

    /* 관리자 회원 검색(페이징 완료)*/
    @GetMapping("/admin/search")
    public String searchMembers(@ModelAttribute MemberSearchForm memberSearchForm, Model model,
                                @RequestParam(value="page", required=false, defaultValue="1") int page){
        model.addAttribute("memberSearchForm", memberSearchForm);
        model.addAttribute("pageSettings", memberService.setSearchMemberListPage(page,memberSearchForm));
        model.addAttribute("memberListByPaging",memberService.getSearchMemberListPage(page,memberSearchForm));

        return "admins/admin-member-search";
    }

    /*회원 아이디/비밀번호 페이지*/
    @GetMapping("/info")
    public String goToIdPwPage(){
        return "info/find-id-pw";
    }

    /*아이디 찾기*/
    @PostMapping("/info/id")
    public String findId(String memberEmail, Model model){
        model.addAttribute("memberSearchDTO", memberInfoService.getMemberIdByEmail(memberEmail));
        return "info/find-id";
    }

    /*아이디 찾기 성공 페이지*/
    @GetMapping("/info/id/find-success")
    public String findIdSuccess(){
        return "members/member-login";
    }

    /*비밀번호 찾기*/
    @PostMapping("/info/pw")
    public String findPw(){
        return"info/reset-pw";
    }

    /*비빌번호 재설정 페이지*///아이디 찾기와 비밀번호 찾기가 한페이지에서 출력되기때문에 나눌필요없음
//    @GetMapping("/info/pw/update")
//    public String goToPwUpdatePage(){
//        return "info/reset-pw";
//    }

    /*비밀번호 재설정 */
    @PostMapping("/info/pw/update")
    public String updatePw(){
        return "info/reset-pw-ok";
    }

    /*비밀번호 재설정 완료*/
    @GetMapping("/info/pw/update-success")
    public String updatePwSuccess (){
        return "info/reset-pw-ok";
    }
}


