package com.example.shoppingmall.qna.controller;

import com.example.shoppingmall.qna.dto.QnaUpdateDTO;
import com.example.shoppingmall.qna.service.QnaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class QnaMemberC {
    private final QnaService qnaService;

    @GetMapping("/{memberNo}/qna")
    public String showMemberQnaList(@RequestParam(value="page", required=false, defaultValue="1") int page,
                                    @PathVariable(name="memberNo") Long memberNo, Model model,
                                    HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/members/login";
        }

        model.addAttribute("pageSettings", qnaService.setMQnaListPage(page,memberNo));
        model.addAttribute("qnaListByPaging",qnaService.getMQnaListPage(page,memberNo));


        return "/qna/member-qna";
    }

    @GetMapping("/{memberNo}/qna/{qnaNo}/update")
    public String updateQnaQuestion(@PathVariable(name="qnaNo")Long qnaNo, Model model){
        model.addAttribute("qnaDetail",qnaService.getQnaInfo(qnaNo));

        return "/qna/member-qna-update";
    }

    @PostMapping("/{memberNo}/qna/{qnaNo}/update")
    public String updateQnaQuestionSuccess(@PathVariable(name="qnaNo")Long qnaNo,
                                           @ModelAttribute QnaUpdateDTO qnaUpdateDTO, Model model){
        Long itemNo =qnaService.getQnaInfo(qnaNo).getItemNo();
        qnaService.modifyQna(qnaNo,qnaUpdateDTO);

        return "redirect:/items/" + itemNo + "/qna/" + qnaNo;
    }

    @PostMapping("/{memberNo}/qna")
    public String deleteQnaQuestion(@PathVariable(name = "memberNo") Long memberNo,
                                    @RequestParam(name = "qnaNo") Long qnaNo){
        qnaService.deleteQustion(qnaNo);


        return "redirect:/members/{memberNo}/qna";
    }
}
