package com.example.shoppingmall.review.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSearchForm {
    private String category;
    private String keyword;
    private Integer startPage;
    private Integer perPageReview;
}

