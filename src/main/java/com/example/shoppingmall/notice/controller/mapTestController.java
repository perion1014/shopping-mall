package com.example.shoppingmall.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")

public class mapTestController {
    @GetMapping("")
    public String showmap(){
        return "fragments/kakaomap";

    }
}
