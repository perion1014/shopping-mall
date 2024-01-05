package com.example.shoppingmall.review.controller;

import com.example.shoppingmall.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
    public class ReviewMemberC {

    private final ReviewService reviewService;

//    /members/'+${ReviewDTO.memberNo}+'/reviews/'+${ReviewDTO.reviewNo}
    @PostMapping("/{memberNo}/reviews/{reviewNo}")
    public String deleteMyReview(@PathVariable(name = "reviewNo") Long reviewNo,
                                 @PathVariable("memberNo") Long memberNo
                                 ) {
        reviewService.deleteReview(reviewNo);
        reviewService.updateItemGradeForDelete(reviewNo);
        return "redirect:/members/{memberNo}/reviews";
    }

    // 마이페이지 페이징
    // 마이페이지에서 자기 리뷰 가져오고 페이징
    @GetMapping("{memberNo}/reviews")
    public String showMyReviewList(@RequestParam(value="page", required=false, defaultValue="1") int page,
                                   @PathVariable("memberNo") Long memberNo, Model model) {

        model.addAttribute("pageSettings", reviewService.setReviewPageByMember(page,memberNo));
        model.addAttribute("memberReviewList", reviewService.getReviewListByMember(memberNo,page));

        return "reviews/member-reviews";
    }
}

