package com.example.shoppingmall.review.controller;

import com.example.shoppingmall.review.dto.ReviewAddDTO;
import com.example.shoppingmall.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 등록
    @GetMapping("{memberNo}/{itemNo}/add")
    public String makeReview(@PathVariable(name="memberNo") Long memberNo,
                             @PathVariable(name="itemNo") Long itemNo, Model model) {

        return "reviews/review-add";
    }

    @PostMapping("{memberNo}/{itemNo}/add")
    public String makeReviewSuccess(@PathVariable(name="memberNo") Long memberNo,
                                    @PathVariable(name="itemNo") Long itemNo,
                                    @ModelAttribute ReviewAddDTO reviewAddDTO){

        reviewAddDTO.setItemNo(itemNo);
        reviewAddDTO.setMemberNo(memberNo);

        reviewService.addReview(reviewAddDTO);

        return "reviews/review-add";
    }

    @GetMapping("{itemNo}")
    public String showItemReviewList(@PathVariable("itemNo") Long itemNo, Model model){

        reviewService.getReviewListByitemNo(itemNo);
        return "reviews/item-review";
    }
}
