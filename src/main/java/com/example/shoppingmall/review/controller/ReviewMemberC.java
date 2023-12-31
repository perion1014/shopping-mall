package com.example.shoppingmall.review.controller;

import com.example.shoppingmall.review.service.ReviewService;
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
    @PostMapping("/{memberNo}/reviews")
    public String deleteMyReview(@RequestParam(name = "reviewNo") Long reviewNo,
                                 @PathVariable("memberNo") Long memberNo
                                 ) {

        Long itemNo = reviewService.getItemNoByReviewNo(reviewNo);

        reviewService.deleteReview(reviewNo);
        reviewService.updateItemGrade(itemNo);

        return "redirect:/members/{memberNo}/reviews";
    }

    // 마이페이지 페이징
    // 마이페이지에서 자기 리뷰 가져오고 페이징
    @GetMapping("{memberNo}/reviews")
    public String showMyReviewList(@RequestParam(value="page", required=false, defaultValue="1") int page,
                                   @PathVariable("memberNo") Long memberNo, Model model) {

        model.addAttribute("pageSettings", reviewService.setReviewPageByMember(page,memberNo));
        model.addAttribute("memberReviewList", reviewService.getReviewListByMember(memberNo,page));

        return "reviews/member-review";
    }
}

