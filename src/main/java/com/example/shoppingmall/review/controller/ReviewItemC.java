package com.example.shoppingmall.review.controller;

import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ReviewItemC {


    private final ReviewService reviewService;


    @GetMapping("/{itemNo}/reviews")
    public  String showItemReviewList(@RequestParam(value="page", required=false, defaultValue="1") int page,
                                  @PathVariable(name="itemNo") Long itemNo, Model model) {

        model.addAttribute("itemNo", itemNo);

        model.addAttribute("itemName", reviewService.getItemName(itemNo));

        model.addAttribute("pageSettings", reviewService.setReviewPageByItemNo(page,itemNo));
        model.addAttribute("itemReviewList",reviewService.getReviewListByItemNo(page, itemNo));


        return "reviews/item-review-more";
    }


}
