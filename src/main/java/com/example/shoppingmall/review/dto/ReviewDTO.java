package com.example.shoppingmall.review.dto;


import com.example.shoppingmall.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long reviewNo;
    private Long memberNo;
    private Long itemNo;

    private String reviewContext;
    private Timestamp reviewCreatedTime;
    private Long reviewScore;

    private String itemSize;

    public static ReviewDTO reivewToReviewDTO(Review review, String itemSize) {

        ReviewDTO reviewDTO = new ReviewDTO();

        return reviewDTO;
        }
    }
