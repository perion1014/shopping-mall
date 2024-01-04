package com.example.shoppingmall.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Long reviewNo;
    private Long memberNo;
    private Long itemNo;
    private String reviewContext;
    private Timestamp reviewCreatedTime;
    private Long reviewScore;
    private Long memberOrderDetailNo;
}
