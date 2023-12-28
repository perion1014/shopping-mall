package com.example.shoppingmall.qna.controller;

import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ConflictAvoid {


    private final ItemService itemService;
    private final QnaService qnaService;



}
