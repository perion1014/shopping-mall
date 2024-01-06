package com.example.shoppingmall.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewMemberControllerKHM {

    @GetMapping("/members/review-list")
    public String reviewListTest(){
        return "member-review";
    }
}
