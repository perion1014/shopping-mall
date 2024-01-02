package com.example.shoppingmall.review.controller;

import com.example.shoppingmall.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
    public class ReviewMemberC {

    private final ReviewService reviewService;

//    /members/'+${ReviewDTO.memberNo}+'/reviews/'+${ReviewDTO.reviewNo}
    @PostMapping("/{memberNo}/reviews/{reviewNo}")
    public String deleteMyReview(@PathVariable(name = "reviewNo") Long reviewNo,
                                 HttpServletRequest req) {
        String currentURI = req.getRequestURI();
        System.out.println("안녕");

//        reviewService.deleteReview(reviewNo);
        return "redirect:" + currentURI;
    }
}
