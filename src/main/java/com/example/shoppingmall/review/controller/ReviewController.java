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

    // 주문 목록 페이지 (test)
    @GetMapping("{memberNo}/order")
    public String showMemberOrderList(@PathVariable(name="memberNo") Long memberNo, Model model) {

        return "reviews/order-page-test";
    }

    //리뷰 등록
    @GetMapping("{memberNo}/{itemNo}/add")
    public String makeReview(@PathVariable(name="memberNo") Long memberNo,
                             @RequestParam(name = "memberOrderNo") Long memberOrderNo,
                             @PathVariable(name="itemNo") Long itemNo, Model model) {
        model.addAttribute("memberOrderNo", memberOrderNo);

        return "reviews/review-add";
    }

    @PostMapping("{memberNo}/{itemNo}/add")
    public String makeReviewSuccess(@PathVariable(name="memberNo") Long memberNo,
                                    @PathVariable(name="itemNo") Long itemNo,
                                    @ModelAttribute ReviewAddDTO reviewAddDTO){

        reviewAddDTO.setItemNo(itemNo);
        reviewAddDTO.setMemberNo(memberNo);

        reviewService.addReview(reviewAddDTO);

        return "redirect:/reviews/{itemNo}";
    }

    // item 상세 에서의 review
    @GetMapping("{itemNo}")
    public String showItemReviewList(@PathVariable("itemNo") Long itemNo, Model model){

        model.addAttribute("itemReviewList",reviewService.getReviewListByitemNo(itemNo));
        System.out.println(reviewService.getReviewListByitemNo(itemNo));
        return "reviews/item-review";
    }

}
