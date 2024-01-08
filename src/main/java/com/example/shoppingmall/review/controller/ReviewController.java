package com.example.shoppingmall.review.controller;

import com.example.shoppingmall.review.dto.ReviewAddDTO;
import com.example.shoppingmall.review.form.ReviewSearchForm;
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
                             @RequestParam(name = "memberOrderDetailNo") Long memberOrderDetailNo,
                             @PathVariable(name="itemNo") Long itemNo, Model model) {
        model.addAttribute("memberOrderDetailNo", memberOrderDetailNo);

        return "reviews/review-add";
    }

    @PostMapping("{memberNo}/{itemNo}/add")
    public String makeReviewSuccess(@PathVariable(name="memberNo") Long memberNo,
                                    @PathVariable(name="itemNo") Long itemNo,
                                    @ModelAttribute ReviewAddDTO reviewAddDTO){

        reviewAddDTO.setItemNo(itemNo);
        reviewAddDTO.setMemberNo(memberNo);

        reviewService.addReview(reviewAddDTO);

        reviewService.updateItemGrade(itemNo);

//        return "redirect:/members/{memberNo}/reviews";
        return "reviews/enroll-success";
    }

    // item 상세 에서의 review
    @GetMapping("{itemNo}")
    public String showItemReviewList(@PathVariable("itemNo") Long itemNo, Model model){

        model.addAttribute("itemReviewList",reviewService.getReviewListByitemNo(itemNo));
        return "reviews/item-review";
    }


    // 어드민 상에서 review 다 가져오고 페이징
    @GetMapping("")
    public String showReviewList(@RequestParam(value="page", required=false, defaultValue="1") int page, Model model) {

        model.addAttribute("pageSettings", reviewService.setReviewPage(page));
        model.addAttribute("reviewList", reviewService.getAllReviewList(page));

        return "admins/admin-review";

    }

    // 어드민 리뷰 삭제
    @PostMapping("delete")
    public String deleteReview(@RequestParam(name = "reviewNo") Long reviewNo) {

        Long itemNo = reviewService.getItemNoByReviewNo(reviewNo);

        reviewService.deleteReview(reviewNo);
        reviewService.updateItemGrade(itemNo);
        return "redirect:/reviews";
    }
    @GetMapping("search")
    public String searchReviews(@ModelAttribute ReviewSearchForm reviewSearchForm,Model model,
                                @RequestParam(value="page", required=false, defaultValue="1") int page) {
        model.addAttribute("reviewSearchForm", reviewSearchForm );
        model.addAttribute("pageSettings", reviewService.setSearchedReviewPage(page,reviewSearchForm) );
        model.addAttribute("reviewListByPaging", reviewService.getSearchedReviewList(page, reviewSearchForm));

        return "admins/admin-search-review";
    }

}
