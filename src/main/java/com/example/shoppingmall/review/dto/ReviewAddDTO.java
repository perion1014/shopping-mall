package com.example.shoppingmall.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewAddDTO {

    private Long memberNo;
    private Long ItemNo;
    private Long memberOrderDetailNo;
    private Long reviewScore;
    private String reviewContext;

}
