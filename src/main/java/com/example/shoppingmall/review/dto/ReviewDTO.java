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
    private Long memberOrderDetailNo;

    private String reviewContext;
    private Timestamp reviewCreatedTime;
    private Long reviewScore;
    private String itemSize;
    private String memberId;
    private String itemName;

    public static ReviewDTO reivewToReviewDTO(Review review, String itemSize, String memberId) {

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setReviewNo(review.getReviewNo());
        reviewDTO.setMemberNo(review.getMemberNo());
        reviewDTO.setItemNo(review.getItemNo());
        reviewDTO.setMemberOrderDetailNo(review.getMemberOrderDetailNo());
        reviewDTO.setReviewContext(review.getReviewContext());
        reviewDTO.setReviewCreatedTime(review.getReviewCreatedTime());
        reviewDTO.setReviewScore(review.getReviewScore());

        reviewDTO.setMemberId(memberId);
        reviewDTO.setItemSize(itemSize);

        return reviewDTO;
        }
    }
