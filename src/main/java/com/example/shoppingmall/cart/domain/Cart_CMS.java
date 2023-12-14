package com.example.shoppingmall.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart_CMS {
    private Long cartNo;
    private Long memberNo;
    private Long itemNo;
    private Integer itemQuantity;



}
