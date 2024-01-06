package com.example.shoppingmall.member.controller;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.*;
import com.example.shoppingmall.member.exception.DuplicatedFieldException;
import com.example.shoppingmall.member.form.MemberSearchForm;
import com.example.shoppingmall.member.service.MemberInfoService;
import com.example.shoppingmall.member.service.MemberLoginService;
import com.example.shoppingmall.member.service.MemberService;
import com.example.shoppingmall.member.validation.MemberValidationSequence;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberLoginService memberLoginService;
    private final MemberInfoService memberInfoService;

    /* 유저 로그인 */
    @GetMapping("/login")
    public String goToMemberLoginPage(@ModelAttribute("memberLoginDTO") MemberLoginDTO memberLoginDTO){
        return "members/member-login";
    }

    @PostMapping("/login")
    public String memberLogin(@Validated(MemberValidationSequence.class) @ModelAttribute("memberLoginDTO") MemberLoginDTO memberLoginDTO, BindingResult bindingResult,HttpServletRequest request){
        //로그인 실패 1 (필드 에러 1)
        if(bindingResult.hasErrors()){
            return "members/member-login";
        }

        Member loginMember = memberLoginService.login(memberLoginDTO);

        //로그인 실패 2 (아이디와 비밀번호 불일치)
        if(loginMember == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 일치하지 않습니다.");
            return "members/member-login";
        }

        //로그인 성공 시
        HttpSession session = request.getSession();
        session.setAttribute("loginMember",loginMember);
        session.setMaxInactiveInterval(1800);
        return "redirect:/";



    }

    /* 유저 로그 아웃 */
    @PostMapping("/logout")
    public String memberLogout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

    /* 유저 회원 가입 기능 */
    @GetMapping("/add")
    public String goToAddMemberPage(@ModelAttribute("memberAddDTO") MemberAddDTO memberAddDTO){
        return "members/add-member";
    }

    @PostMapping("/add")
    public String addMember(@Validated(MemberValidationSequence.class) @ModelAttribute("memberAddDTO") MemberAddDTO memberAddDTO, BindingResult bindingResult){
        //회원 가입 실패 1 (필드 유효성 에러)
        if(bindingResult.hasErrors()){
            return "members/add-member";
        }

        //회원 가입 실패 2 (비밀번호와 비밀번호 확인 불일치)
        if(!memberAddDTO.getMemberPw().equals(memberAddDTO.getMemberPw2())){
            bindingResult.rejectValue("memberPw2",null,"비밀번호가 일치하지 않습니다.");
        }

        //성공 시
        try{
            memberService.join(memberAddDTO);
            return "redirect:/members/add-success";
        }

        //회원 가입 실패 3 (중복된 아이디나 핸드폰 번호, 이메일이 존재할 경우)
        catch (DuplicatedFieldException e){
            bindingResult.rejectValue(e.getFieldName(),null,e.getMessage());
            return "members/add-member";
        }

    }

    @GetMapping("/add-success")
    public String goToAddMemberSuccess(){
        return "members/add-member-success";
    }


    /* 유저 회원 정보 메뉴 페이지 */
    @GetMapping("/{memberNo}/show-info")
    public String showMemberInfo(@PathVariable(name="memberNo") Long memberNo){
        return "members/member-info";
    }

    /* 유저 회원 개인 정보 조회 및 수정 */
    @GetMapping("/{memberNo}/update")
    public String goToUpdateMemberPage(@PathVariable(name="memberNo") Long memberNo, Model model){
        model.addAttribute("memberUpdateDTO",memberService.getMemberInfo(memberNo));
        return "members/update-member";
    }

    /* 유저 회원 개인 정보 수정 */
    @PostMapping("/{memberNo}/update")
    public String updateMemberInfo(@Validated(MemberValidationSequence.class) @ModelAttribute MemberUpdateDTO memberUpdateDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //회원 수정 실패 1 (필드 유효성 에러)
        if(bindingResult.hasErrors()){
            return "members/update-member";
        }

        //회원 수정 실패 2 (비밀번호와 비밀번호 확인 불일치)
        if(!memberUpdateDTO.getMemberPw().equals(memberUpdateDTO.getMemberPw2())){
            bindingResult.rejectValue("memberPw2",null,"비밀번호가 일치하지 않습니다.");
            return "members/update-member";
        }

        try{
            memberService.update(memberUpdateDTO);
            redirectAttributes.addFlashAttribute("memberUpdateSuccess", "회원 정보가 업데이트되었습니다.");
            return "redirect:/members/{memberNo}/update";

        //회원 수정 실패 3 (중복된 핸드폰 번호가 존재)
        }catch (DuplicatedFieldException e){
            bindingResult.rejectValue(e.getFieldName(),null,e.getMessage());
            return "members/update-member";
        }
    }

    /* 유저 회원 정보 탈퇴 */
    @GetMapping("/{memberNo}/delete")
    public String goToDeleteMemberPage(@PathVariable(name="memberNo") Long memberNo, Model model){
        model.addAttribute("memberDeleteDTO",new MemberDeleteDTO());
        return "members/delete-member";
    }

    @PostMapping("/{memberNo}/delete")
    public String deleteMember(@PathVariable(name="memberNo") Long memberNo,@Validated(MemberValidationSequence.class) @ModelAttribute MemberDeleteDTO memberDeleteDTO, BindingResult bindingResult, HttpServletRequest request){

        //회원 탈퇴 실패 1 (필드 유효성 에러)
        if(bindingResult.hasErrors()){
            return "members/delete-member";
        }

        //회원 탈퇴 실패 2 (비밀번호와 비밀번호 확인 불일치)
        if(!memberDeleteDTO.getMemberPw().equals(memberDeleteDTO.getMemberPw2())){
            bindingResult.rejectValue("memberPw2",null,"비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return "members/delete-member";
        }

        try{
            memberService.withdraw(memberNo, memberDeleteDTO);
            HttpSession session = request.getSession(false);
            session.invalidate();
            return "redirect:/members/delete-success";
        }catch (IllegalStateException e){
            bindingResult.rejectValue("memberPw",null,e.getMessage());
            return "members/delete-member";
        }

    }

    @GetMapping("/delete-success")
    public String goToDeleteMemberSuccess(){
        return "members/delete-member-success";
    }


    /* 관리자 회원 목록 조회(페이징 처리 완료) */
    @GetMapping("/admin")
    public String showMemberList(Model model,
                         @RequestParam(value="page", required=false, defaultValue="1") int page) {

        model.addAttribute("pageSettings", memberService.setMemberListPage(page));
        model.addAttribute("memberListByPaging",memberService.getMemberListPage(page));

        return "admins/admin-member";
    }

    /* 관리자 회원 추방 */
    @PostMapping("/delete")
    public String deleteMembers(@RequestParam("memberNo")Long memberNo, RedirectAttributes redirectAttributes){
        memberService.drop(memberNo);
        redirectAttributes.addFlashAttribute("memberUpdateSuccess", "회원 추방이 완료되었습니다.");
        return "redirect:/members/admin";
    }

    /* 관리자 회원 검색(페이징 완료) */
    @GetMapping("/admin/search")
    public String searchMembers(@ModelAttribute MemberSearchForm memberSearchForm, Model model,
                                @RequestParam(value="page", required=false, defaultValue="1") int page){
        model.addAttribute("memberSearchForm", memberSearchForm);
        model.addAttribute("pageSettings", memberService.setSearchMemberListPage(page,memberSearchForm));
        model.addAttribute("memberListByPaging",memberService.getSearchMemberListPage(page,memberSearchForm));

        return "admins/admin-member-search";
    }

    /* 회원 아이디 / 비밀번호 페이지 */
    @GetMapping("/info")
    public String goToIdPwPage(){
        return "info/find-id-pw";
    }


    /* 아이디 찾기 및 성공 결과 반환 페이지*/
    @PostMapping("/info/id/find-success")
    public String findIdSuccess(@Validated @RequestParam("memberEmail")String memberEmail, Model model){
        model.addAttribute("memberIdDTO", memberInfoService.getMemberIdByEmail(memberEmail));
        return "info/find-id";
    }

    /*비밀번호 찾기*/
    @PostMapping("/info/pw")
    public String findPw(){
        return"info/find-pw";
    }

    /*비빌번호 재설정 페이지*///아이디 찾기와 비밀번호 찾기가 한페이지에서 출력되기때문에 나눌필요없음
//    @GetMapping("/info/pw/update")
//    public String goToPwUpdatePage(){
//        return "info/reset-pw";
//    }

    /*비밀번호 재설정 */
    @PostMapping("/info/pw/update")
    public String updatePw(){
        return "info/find-pw-ok";
    }

    /*비밀번호 재설정 완료*/
    @GetMapping("/info/pw/update-success")
    public String updatePwSuccess (){
        return "info/find-pw-ok";
    }
}


