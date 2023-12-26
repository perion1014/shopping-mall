package com.example.shoppingmall.qna.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {

    @GetMapping("/{itemNo}/add")
    public String makeQnaQuestion(@PathVariable(name="itemNo") Long itemNo,Model model) {
        return "qna/qna-add";
    }
}
