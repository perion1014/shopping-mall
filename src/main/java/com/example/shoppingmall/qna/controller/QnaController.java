package com.example.shoppingmall.qna.controller;

import com.example.shoppingmall.qna.dto.QnaAddDTO;
import com.example.shoppingmall.qna.service.QnaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;



@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;
    @GetMapping("{memberNo}/{itemNo}")
    public String makeQnaQuestion(@PathVariable(name="itemNo") Long itemNo,
                                  @PathVariable(name="memberNo") Long memberNo) {
        return "qna/qna-add";
    }

    @PostMapping("{memberNo}/{itemNo}")
    public String makeQnaQuestionSuccess(@PathVariable(name="itemNo") Long itemNo,
                                         @PathVariable(name="memberNo") Long memberNo, Model model,
                                         @ModelAttribute QnaAddDTO qnaAddDTO, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/members/login";
        }

        qnaService.addQna(itemNo,memberNo,qnaAddDTO);

        return "redirect:/items/{itemNo}";
    }
}
